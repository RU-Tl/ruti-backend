package com.toj.dto.daily;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "달성률 조회 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDailyRateResponse {

    @Schema(description = "성공률", example = "57")
    private double successRate;

    public static GetDailyRateResponse of(double successRate) {
        return new GetDailyRateResponse(successRate);
    }


}
