package com.toj.dto.routine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRoutineResponse {
    private Long routineId;
    private String routineCategories;
    private String routineContent;
    private String routineAlarmTime;
    private String routineStatus;
}
