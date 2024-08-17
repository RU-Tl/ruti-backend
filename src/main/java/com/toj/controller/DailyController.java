package com.toj.controller;

import com.toj.dto.daily.*;
import com.toj.entity.RoutineCate;
import com.toj.global.model.ApiResponse;
import com.toj.service.DailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daily")
@Tag(name = "daily", description = "반복 루틴 API")
public class DailyController {

    private final DailyService dailyService;

    @Operation(
            summary = "루틴(일상) 상세조회 API",
            description = """
                    홈 화면에서 루틴 상세 조회를 합니다.\s
                    개별 루틴 상세 최근 7일 내역이 조회가됩니다.
                    날짜별 성공,실패 여부와 스코어가 조회됩니다."""
    )
    @GetMapping("/{memberId}/{routineId}")
    public ApiResponse <List<GetDetailDailyResponse>> getDetailDaily(
            @PathVariable Long memberId,
            @PathVariable Long routineId) {

        return ApiResponse.success(
                dailyService.getDetailDaily(memberId,routineId));
    }

    @Operation(
            summary = "루틴 주간 스코어 합산 API",
            description = "루틴 상세 조회 페이지에서 주간 스코어 합산을 합니다."
    )
    @GetMapping("/weekly/{memberId}/{routineId}")
    public ApiResponse<GetWeeklyScoreResponse> getWeeklyScore(
            @PathVariable Long memberId,
            @PathVariable Long routineId) {

        return ApiResponse.success(
                dailyService.getWeeklyScore(memberId, routineId));
    }

    @Operation(
            summary = "루틴 토탈 스코어 합산 API",
            description = "루틴 상세 조회 페이지에서 총 스코어 합산을 합니다."
    )
    @GetMapping("/total/{memberId}/{routineId}")
    public ApiResponse<GetTotalScoreResponse> getTotalScore(
            @PathVariable Long memberId,
            @PathVariable Long routineId) {

        return ApiResponse.success(
                dailyService.getTotalScore(memberId, routineId));
    }

    @Operation(
            summary = "루틴 랭킹 API",
            description = "루틴 상세 조회 페이지에서 " +
                    "총 스코어를 기준으로 자신 랭킹을 조회 합니다."
    )
    @GetMapping("/ranking/{memberId}/{routineCate}")
    public ApiResponse<Optional<GetRankingResponse>> getRanking(
            @PathVariable Long memberId,
            @PathVariable RoutineCate routineCate) {

        return ApiResponse.success(
                dailyService.getRanking(memberId, routineCate));
    }

    @Operation(
            summary = "카테고리별 랭킹 리스트 조회 API",
            description = "카테고리별 랭킹 리스트(1~10위)를 조회 합니다."
    )
    @GetMapping("/ranking/list/{memberId}/{routineCate}")
    public ApiResponse <List<GetRankingResponse>> getRankingList(
            @PathVariable Long memberId,
            @PathVariable RoutineCate routineCate) {

        return ApiResponse.success(
                dailyService.getRankingList(memberId, routineCate));
    }

    @Operation(
            summary = "루틴 달성률 API",
            description = "루틴 상세 조회 페이지에서 " +
                    "달성률을 조회 합니다."
    )
    @GetMapping("/rate/{memberId}/{routineId}")
    public ApiResponse<GetDailyRateResponse> getRate(
            @PathVariable Long memberId,
            @PathVariable Long routineId) {

        return ApiResponse.success(
                dailyService.getRate(memberId, routineId));
    }

    @Operation(
            summary = "캘린더 조회 API",
            description = "캘린더를 카테고리별로 조회 합니다."
    )
    @GetMapping("/calendar/{memberId}/{month}/{routineCate}")
    public ApiResponse<List<GetCalendarResponse>> getCalendar(
            @PathVariable Long memberId,
            @PathVariable int month,
            @PathVariable RoutineCate routineCate) {

        return ApiResponse.success(
                dailyService.getCalendar(memberId, month, routineCate));
    }

}
