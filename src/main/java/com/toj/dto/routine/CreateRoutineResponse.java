package com.toj.dto.routine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRoutineResponse {

    private Long routineId;

    public static CreateRoutineResponse of(Long routineId) {
        return new CreateRoutineResponse(routineId);
    }
}
