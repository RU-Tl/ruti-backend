package com.toj.service;

import com.toj.dto.daily.GetDetailDailyResponse;
import com.toj.dto.daily.GetWeeklyScoreResponse;
import com.toj.entity.Daily;
import com.toj.global.code.ErrorCode;
import com.toj.global.error.exception.ForbiddenException;
import com.toj.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
}
