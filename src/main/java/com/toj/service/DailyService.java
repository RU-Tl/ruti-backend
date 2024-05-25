package com.toj.service;

import com.toj.dto.daily.*;
import com.toj.entity.Daily;
import com.toj.entity.RoutineCate;
import com.toj.global.code.ErrorCode;
import com.toj.global.error.exception.ForbiddenException;
import com.toj.repository.daily.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyService {

    private final DailyRepository dailyRepository;

    // 루틴 상세 조회
    public List<GetDetailDailyResponse> getDetailDaily(Long memberId, Long routineId) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);

        List<Daily> daily = dailyRepository.findByRoutineIdAndRegTimeBetween(routineId, startDate, endDate);

        if (!daily.get(0).getRoutine().getMember().getId().equals(memberId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION);
        }

        List<GetDetailDailyResponse> responseList = daily.stream()
                .map(d -> GetDetailDailyResponse.of(
                        d.getId(), d.getDailyCate().getValue(),
                        d.getScore(), d.getRegTime()))
                .collect(Collectors.toList());

        return responseList;
    }

    // 주간 스코어 합산
    public GetWeeklyScoreResponse getWeeklyScore(Long memberId, Long routineId) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);

        List<Daily> daily = dailyRepository.findByRoutineIdAndRegTimeBetween(routineId, startDate, endDate);

        if (!daily.get(0).getRoutine().getMember().getId().equals(memberId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION);
        }

        int total = 0;

        for (Daily d : daily) {
            int score = d.getScore();
            total += score;
        }

        return GetWeeklyScoreResponse.of(total);
    }

    // 토탈 스코어 합산
    public GetTotalScoreResponse getTotalScore(Long memberId, Long routineId) {

        List<Daily> dailyList = dailyRepository.findByRoutineId(routineId);

        if (!dailyList.get(0).getRoutine().getMember().getId().equals(memberId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION);
        }

        int total = 0;

        for (Daily d : dailyList) {
            int score = d.getScore();
            total += score;
        }

        return GetTotalScoreResponse.of(total);
    }
    
    // 자신 랭킹 조회
    public Optional<GetRankingResponse> getRanking(Long memberId, RoutineCate routineCate) {

        List<GetRankingResponse> rankingWithTotalScore = getRankingWithTotalScore(routineCate);

        return rankingWithTotalScore.stream()
                .filter(r -> r.getMemberId().equals(memberId))
                .findFirst();
    }

    // 랭킹 리스트 조회
    public List<GetRankingResponse> getRankingList(Long memberId, RoutineCate routineCate) {

        return getRankingWithTotalScore(routineCate);
    }

    @NotNull
    private List<GetRankingResponse> getRankingWithTotalScore(RoutineCate routineCate) {
        List<GetRankingResponse> rankingWithTotalScore = dailyRepository.getRankingWithTotalScore(routineCate);

        // 점수 순으로 내림차순 정렬 (점수같으면 memberId로 오름차순)
        rankingWithTotalScore.sort(Comparator.comparing(GetRankingResponse::getTotalScore).reversed()
                .thenComparing(GetRankingResponse::getMemberId));

        // 순위 계산 및 설정
        AtomicInteger rank = new AtomicInteger(0); // 0부터 순위 시작
        GetRankingResponse previous = null; // 이전 랭킹 정보 저장
        for (GetRankingResponse current : rankingWithTotalScore) {
            if (previous == null || !current.getTotalScore().equals(previous.getTotalScore())) {
                // 이전 멤버와 점수가 다르면 순위 증가
                rank.getAndIncrement();
            }
            // 현재 멤버의 순위 설정
            current.setRanking(rank.get());
            previous = current;
        }
        return rankingWithTotalScore;
    }

    // 루틴 1주일간 달성률
    public GetDailyRateResponse getRate(Long memberId, Long routineId) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);

        List<Daily> daily = dailyRepository.findByRoutineIdAndRegTimeBetween(routineId, startDate, endDate);

        if (!daily.get(0).getRoutine().getMember().getId().equals(memberId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION);
        }

        long successCount = daily.stream()
                .filter(d -> "SUCCESS".equals(d.getDailyCate().name()))
                .count();

        double successRate = ((double) successCount / daily.size()) * 100;

        return GetDailyRateResponse.of(successRate);
    }

}
