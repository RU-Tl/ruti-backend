package com.toj.dto.daily;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDailyRateResponse {

    private double successRate;

    public static GetDailyRateResponse of(double successRate) {
        return new GetDailyRateResponse(successRate);
    }


}
