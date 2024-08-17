package com.toj.controller;

import com.toj.dto.daily.EditRoutineRequest;
import com.toj.dto.daily.EditRoutineResponse;
import com.toj.dto.routine.*;
import com.toj.global.model.ApiResponse;
import com.toj.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routine")
@Tag(name = "routine", description = "루틴 관련 API")
public class RoutineController {

    private final RoutineService routineService;

    @Operation(
            summary = "루틴 등록 API",
            description = "루틴 정보를 저장합니다."
    )
    @PostMapping("/{memberId}")
    public ApiResponse<CreateRoutineResponse> createRoutine(@RequestBody CreateRoutineRequest request, @PathVariable Long memberId) {
        return ApiResponse.success(new CreateRoutineResponse(routineService.createRoutine(request, memberId)));
    }

    @Operation(
            summary = "루틴 전체 조회 API",
            description = "등록된 전체 루틴이 조회됩니다."
    )
    @GetMapping("/{memberId}/{selectedDate}")
    public ApiResponse<List<GetAllRoutineResponse>> getAllRoutine(@PathVariable Long memberId, @PathVariable LocalDate selectedDate) {
        return ApiResponse.success(routineService.findAllByMemberId(memberId, selectedDate));
    }

    @DeleteMapping("/{routineId}")
    public ApiResponse<RemoveRoutineResponse> deleteRoute(@PathVariable Long routineId) {
        return ApiResponse.success(routineService.deleteByRoutineId(routineId));
    }

    @Operation(
            summary = "루틴 상세 제목 수정 API",
            description = "루틴 상세 제목이 변경됩니다."
    )
    @PatchMapping("/daily/{routineId}")
    public ApiResponse<EditRoutineResponse> createRoutine(@RequestBody EditRoutineRequest request, @PathVariable Long routineId) {
        return ApiResponse.success(routineService.updateRoutine(request, routineId));
    }

    @Operation(
            summary = "루틴 알림 정보 등록 API",
            description = "루틴 알림 정보를 저장합니다."
    )
    @PostMapping("/daily/{routineId}")
    public ApiResponse<CreateDailyResponse> createDaily(@RequestBody CreateDailyRequest request, @PathVariable Long routineId) {

        return ApiResponse.success(new CreateDailyResponse(routineService.createDaily(request, routineId)));
    }
}
