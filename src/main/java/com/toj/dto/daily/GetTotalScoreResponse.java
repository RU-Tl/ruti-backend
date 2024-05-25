package com.toj.dto.daily;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "총합 스코어 조회 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTotalScoreResponse {

    @Schema(description = "총합 스코어", example = "320")
    private int totalScore;

    public static GetTotalScoreResponse of(int total) {
        return new GetTotalScoreResponse(total);
    }
}
