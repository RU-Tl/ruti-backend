package com.toj.dto.routine;

import com.toj.entity.Member;
import com.toj.entity.Routine;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRoutineRequest {
    private String categories;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
