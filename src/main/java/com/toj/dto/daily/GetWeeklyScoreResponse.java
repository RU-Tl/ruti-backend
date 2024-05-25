package com.toj.dto.daily;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "주간 스코어 합계 조회 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetWeeklyScoreResponse {

    @Schema(description = "주간 스코어 합계", example = "120")
    private int weeklyTotalScore;

    public static GetWeeklyScoreResponse of(int total) {
        return new GetWeeklyScoreResponse(total);
    }
}
