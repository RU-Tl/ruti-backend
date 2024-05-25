package com.toj.dto.daily;

import com.toj.entity.RoutineCate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditRoutineResponse {
    private Long routineId;
    private RoutineCate routineCategories;
    private String routineContent;
}
