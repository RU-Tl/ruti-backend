package com.toj.controller;

import com.toj.dto.daily.GetDetailDailyResponse;
import com.toj.global.model.ApiResponse;
import com.toj.service.DailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routine")
@Tag(name = "daily", description = "반복 루틴 API")
public class DailyController {

    private final DailyService dailyService;

    @Operation(
            summary = "루틴(일상) 상세조회 API",
            description = "홈 화면에서 루틴 상세 조회를 합니다. \n\n" +
                    "개별 루틴 상세 내역이 조회가됩니다.\n\n" +
                    "날짜별 성공,실패 여부와 스코어가 조회됩니다."
    )
    @GetMapping("/{routineId}")
    public ApiResponse <List<GetDetailDailyResponse>> getDetailDaily(
            @RequestParam Long memberId,
            @PathVariable("routineId") Long routineId) {

        return ApiResponse.success(
                dailyService.getDetailDaily(memberId,routineId));
    }


}
