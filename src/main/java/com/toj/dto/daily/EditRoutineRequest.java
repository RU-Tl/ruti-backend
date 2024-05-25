package com.toj.dto.daily;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EditRoutineRequest {
    @Schema(description = "루틴 상세제목", example = "아침조깅30분")
    private String content;
}
