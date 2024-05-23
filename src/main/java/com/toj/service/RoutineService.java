package com.toj.service;

import com.toj.dto.routine.CreateRoutineRequest;
import com.toj.dto.routine.CreateRoutineResponse;
import com.toj.entity.Member;
import com.toj.entity.Routine;
import com.toj.exception.NotFoundException;
import com.toj.global.code.ErrorCode;
import com.toj.repository.MemberRepository;
import com.toj.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final MemberRepository memberRepository;

    private Long createRoutine(CreateRoutineRequest request, Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.));

        return CreateRoutineResponse.of();
    }
}
