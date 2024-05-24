package com.toj.controller;

import com.toj.dto.routine.*;
import com.toj.entity.Member;
import com.toj.global.model.ApiResponse;
import com.toj.service.MemberService;
import com.toj.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routine")
public class RoutineController {

    private final MemberService memberService;
    private final RoutineService routineService;

    @PostMapping("/{memberId}")
    public ApiResponse<CreateRoutineResponse> createRoutine(@RequestBody CreateRoutineRequest request, @PathVariable Long memberId) {
        Member findMember = memberService.findById(memberId);

        return ApiResponse.success(new CreateRoutineResponse(routineService.createRoutine(request, findMember)));
    }

    @GetMapping("/{memberId}/{selectedDate}")
    public ApiResponse<List<GetAllRoutineResponse>> getAllRoutine(@PathVariable Long memberId, @PathVariable LocalDateTime selectedDate) {
        return ApiResponse.success(routineService.findAllByMemberId(memberId, selectedDate));
    }

    @PostMapping("/daily/{routineId}")
    public ApiResponse<CreateDailyResponse> createDaily(@RequestBody CreateDailyRequest request, @PathVariable Long routineId) {

        return ApiResponse.success(new CreateDailyResponse(routineService.createDaily(request, routineId)));
    }
}
