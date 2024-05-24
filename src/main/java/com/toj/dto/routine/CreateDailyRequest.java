package com.toj.dto.routine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toj.entity.DailyCate;
import lombok.Data;

@Data
public class CreateDailyRequest {
    private String status;
    private String failReason;
}
