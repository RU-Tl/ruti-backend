package com.toj.dto.routine;

import com.toj.entity.RoutineCate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRoutineResponse {
    @Schema(description = "루틴 id", example = "1")
    private Long routineId;
    @Schema(description = "루틴 카테고리", example = "EXERCISE")
    private RoutineCate routineCategories;
    @Schema(description = "루틴 상세제목", example = "아침운동")
    private String routineContent;
    @Schema(description = "루틴 알림시간", example = "AM 7:20")
    private String routineAlarmTime;
    @Schema(description = "루틴 상태", example = "NONE")
    private String routineStatus;
    @Schema(description =  "루틴 요일", example = "MON, WED, FRI")
    private String days;
}
