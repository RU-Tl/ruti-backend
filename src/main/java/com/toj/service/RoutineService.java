package com.toj.service;

import com.toj.dto.routine.CreateRoutineRequest;
import com.toj.dto.routine.CreateRoutineResponse;
import com.toj.dto.routine.GetAllRoutineResponse;
import com.toj.entity.Daily;
import com.toj.entity.DailyCate;
import com.toj.entity.Member;
import com.toj.entity.Routine;
import com.toj.repository.DailyRepository;
import com.toj.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {
    private final RecordRepository recordRepository;
    private final DailyRepository dailyRepository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Long createRoutine(CreateRoutineRequest request, Member findMember) {
        Routine routine = new Routine(findMember, request.getContent(), request.getCategories(), request.getStartDate(), request.getEndDate(), request.getAlarmTime());
        recordRepository.save(routine);
        return routine.getId();
    }

    public List<GetAllRoutineResponse> findAllByMemberId(Long memberId, String selectedDate) {
        List<GetAllRoutineResponse> result = new ArrayList<>();
//        List<Routine> routineList = recordRepository.findAllByMemberId(memberId).stream()
//                .filter(routine -> routine.getEndDate().isAfter(LocalDate.parse(selectedDate, dateFormatter).atTime(LocalTime.MIDNIGHT)))
//                .collect(Collectors.toList());

//        for (Routine routine : routineList) {
//            Daily res = dailyRepository.findById(routine.getId())
//                    .filter(daily -> daily.getStatus().equals(DailyCate.SUCCESS))
//                    .map(daily -> daily.ge);
//        }

        return result;
    }
}
