package com.toj.dto.daily;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTotalScoreResponse {

    private int totalScore;

    public static GetTotalScoreResponse of(int total) {
        return new GetTotalScoreResponse(total);
    }
}
