package com.toj.dto.daily;

import com.toj.entity.RoutineCate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditRoutineResponse {
    @Schema(description = "루틴 id", example = "1")
    private Long routineId;
    @Schema(description = "루틴 카테고리", example = "EXERCISE")
    private RoutineCate routineCategories;
    @Schema(description = "루틴 상세정보", example = "아침조깅30분")
    private String routineContent;
}
