package com.toj.dto.routine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toj.entity.DailyCate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateDailyRequest {
    @Schema(description = "루틴 상태", example = "SUCCESS")
    private String status;

    private String failReason;
}
