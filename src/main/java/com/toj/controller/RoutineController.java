package com.toj.controller;

import com.toj.dto.routine.CreateRoutineRequest;
import com.toj.dto.routine.CreateRoutineResponse;
import com.toj.global.model.ApiResponse;
import com.toj.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routine")
public class RoutineController {

    private final RoutineService routineService;

    public ApiResponse<CreateRoutineResponse> createRoutine(@RequestBody @Validated CreateRoutineRequest request,
                                                            Long memberId,
                                                            BindingResult bindingResult) {
        return ApiResponse.success(routineService.);
    }
}
