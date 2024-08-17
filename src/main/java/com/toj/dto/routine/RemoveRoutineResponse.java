package com.toj.dto.routine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "루틴 탈퇴 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveRoutineResponse {
    @Schema(description = "루틴 ID", example = "1")
    Long routineId;
}
