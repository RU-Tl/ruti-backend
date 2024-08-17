package com.toj.dto.routine;

import com.toj.entity.Member;
import com.toj.entity.Routine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateRoutineRequest {
    @Schema(description = "루틴 카테고리", example = "EXERCISE")
    private String categories;
    @Schema(description = "루틴 상세제목", example = "아침 조깅")
    private String content;
    @Schema(description = "루틴 시작날짜", example = "2024-06-04")
    private LocalDate startDate;
    @Schema(description = "루틴 종료날짜", example = "2024-07-04")
    private LocalDate endDate;
    @Schema(description = "루틴 알림시간", example = "AM 7:20")
    private String alarmTime;
    @Schema(description = "루틴 요일", example = "MON,TUE,WED")
    private String days;
}
