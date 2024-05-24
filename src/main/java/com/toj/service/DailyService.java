package com.toj.service;

import com.toj.dto.daily.GetDetailDailyResponse;
import com.toj.entity.Daily;
import com.toj.exception.NotFoundException;
import com.toj.global.code.ErrorCode;
import com.toj.global.error.exception.ForbiddenException;
import com.toj.repository.DailyRepository;
import com.toj.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyService {

    private final DailyRepository dailyRepository;

    // daily 상세 조회
    public List<GetDetailDailyResponse> getDetailDaily(Long memberId, Long routineId) {

        List<Daily> daily = dailyRepository.findByRoutineId(routineId);

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
}
