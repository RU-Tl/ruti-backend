package com.toj.dto.daily;

import com.querydsl.core.annotations.QueryProjection;
import com.toj.entity.DailyCate;
import com.toj.entity.RoutineCate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "캘린더 조회 DTO")
public class GetCalendarResponse {

    @Schema(description = "일상 ID", example = "1")
    private Long dailyId;

    @Schema(description = "루틴 상세정보", example = "아침조깅30분")
    private String content;

    @Schema(description = "루틴 카테고리", example = "EXERCISE")
    private RoutineCate routineCate;

    @Schema(description = "알람 시간", example = "AM 7:20")
    private String alarmTime;

    @Schema(description = "일상 카테고리", example = "FAIL")
    private DailyCate dailyCate;

    @Schema(description = "실패 사유", example = "컨디션이 좋지 않았어요")
    private String failReason;

    private LocalDateTime regTime;

    @QueryProjection
    public GetCalendarResponse(Long dailyId, String content, RoutineCate routineCate,
                               String alarmTime, DailyCate dailyCate, String failReason,
                               LocalDateTime regTime) {
        this.dailyId = dailyId;
        this.content = content;
        this.routineCate = routineCate;
        this.alarmTime = alarmTime;
        this.dailyCate = dailyCate;
        this.failReason = failReason;
        this.regTime = regTime;
    }
}
