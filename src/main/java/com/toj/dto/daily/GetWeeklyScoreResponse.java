package com.toj.dto.daily;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetWeeklyScoreResponse {

    private int weeklyTotalScore;

    public static GetWeeklyScoreResponse of(int total) {
        return new GetWeeklyScoreResponse(total);
    }
}
