package com.toj.dto.routine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDailyResponse {

    @Schema(description = "daily id", example = "1")
    Long dailyId;
}
