package com.toj.service;

import com.toj.dto.daily.EditRoutineRequest;
import com.toj.dto.daily.EditRoutineResponse;
import com.toj.dto.routine.CreateDailyRequest;
import com.toj.dto.routine.CreateRoutineRequest;
import com.toj.dto.routine.GetAllRoutineResponse;
import com.toj.dto.routine.RemoveRoutineResponse;
import com.toj.entity.*;
import com.toj.exception.NotFoundException;
import com.toj.repository.MemberRepository;
import com.toj.repository.daily.DailyRepository;
import com.toj.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final DailyRepository dailyRepository;
    private final MemberRepository memberRepository;

    private final int routineMax = 3;

    public Long createRoutine(CreateRoutineRequest request, Long memberId) {
        if (!isRoutineUnderThree(memberId)) {
            throw new IllegalStateException("루틴은 최대 3개까지만 등록할 수 있어요.");
        }
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("잘못된 회원 정보 입니다."));

        Routine routine = new Routine(findMember, request.getContent(), request.getCategories(), request.getStartDate(), request.getEndDate(), request.getAlarmTime(), request.getDaysOfWeek());
        routineRepository.save(routine);
        return routine.getId();
    }

    private boolean isRoutineUnderThree(Long memberId) {
        List<Routine> routineList = routineRepository.findAllByMemberId(memberId).stream()
                .filter(routine -> routine.getEndDate().isAfter(LocalDate.now())) // 현재 진행 중인 루틴인 경우만 참.
                .toList();

        return routineList.size() < routineMax;
    }

    public List<GetAllRoutineResponse> findAllByMemberId(Long memberId, LocalDate selectedDate) {
        List<Routine> routineList = routineRepository.findAllByMemberIdAndDate(memberId, selectedDate);

        List<GetAllRoutineResponse> result = new ArrayList<>();
        for (int i = 0; i < routineList.size(); i++) {
            Routine routine = routineList.get(i);
            Optional<Daily> daily = dailyRepository.findById(routine.getId());
            GetAllRoutineResponse response = new GetAllRoutineResponse();
            if (daily.isPresent()) {
                response.setRoutineStatus(daily.get().getDailyCate());
            } else {
                response.setRoutineStatus(DailyCate.NONE);
            }
            response.setRoutineId(routine.getId());
            response.setRoutineCategories(routine.getRoutineCate());
            response.setRoutineContent(routine.getContent());
            response.setRoutineAlarmTime(routine.getAlarmTime());
            response.setDaysOfWeek(routine.getDaysOfWeek());
            result.add(response);
        }
//        for (Routine routine : routineList) {
//            Optional<Daily> daily = dailyRepository.findById(routine.getId());
//            GetAllRoutineResponse response = new GetAllRoutineResponse();
//            response.setRoutineId(routine.getId());
//            response.setRoutineCategories(routine.getRoutineCate());
//            response.setRoutineContent(routine.getContent());
//            response.setRoutineAlarmTime(routine.getAlarmTime());
//            response.setDaysOfWeek(routine.getDaysOfWeek());
//            if (daily.isPresent()) {
//                response.setRoutineStatus(daily.get().getDailyCate().toString());
//            } else {
//                response.setRoutineStatus("NONE");
//            }
//            result.add(response);
//        }

        return result;
    }

    public Long createDaily(CreateDailyRequest request, Long routineId) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalStateException("저장된 루틴 정보가 없음."));

        DailyCate status = DailyCate.valueOf(request.getStatus());
        Daily daily = new Daily(routine, status, request.getFailReason());
        dailyRepository.save(daily);

        updateTotalScore(routine.getMember().getId(), daily.getScore());

        return daily.getId();
    }

    public void updateTotalScore(Long memberId, int score) {
        Member member = memberRepository.findById(memberId).get();
        member.updateTotalScore(score);

        if (member.getTotalScore() <= 100) {
            member.updateGrade(MemberGrade.RUCOMI);
        } else if (member.getTotalScore() <= 200) {
            member.updateGrade(MemberGrade.RUTINI);
        } else if (member.getTotalScore() <= 300) {
            member.updateGrade(MemberGrade.ROUTINEMASTER);
        } else {
            member.updateGrade(MemberGrade.ROUTINELEVELMAX);
        }
    }

    public EditRoutineResponse updateRoutine(EditRoutineRequest request, Long routineId) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalStateException("저장된 루틴 정보가 없음."));

        routine.update(request.getContent());

        return new EditRoutineResponse(routineId, routine.getRoutineCate(), routine.getContent());
    }

    public RemoveRoutineResponse deleteByRoutineId(Long routineId) {
        routineRepository.deleteById(routineId);

        return new RemoveRoutineResponse(routineId);
    }
}
