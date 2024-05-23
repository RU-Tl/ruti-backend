package com.toj.dto.daily;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDetailDailyResponse {

    private Long routineId;
    private String dailyCate;
    private int score;
    private LocalDateTime regTime;

    public static GetDetailDailyResponse of(
            Long routineId, String dailyCate, int score, LocalDateTime regTime) {
        return new GetDetailDailyResponse(routineId, dailyCate, score, regTime);
    }
}
