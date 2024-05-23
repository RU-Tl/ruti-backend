package com.toj.dto.daily;

import com.toj.entity.DailyCate;
import lombok.Data;

@Data
public class CreateDailyRequest {

    private DailyCate status;
    private String failReason;
}
