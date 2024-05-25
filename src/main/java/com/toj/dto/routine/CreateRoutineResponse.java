package com.toj.dto.routine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRoutineResponse {
    @Schema(description = "루틴 id", example = "1")
    private Long routineId;
}
