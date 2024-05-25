package com.toj.dto.daily;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "루틴(일상) 상세 조회 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetDetailDailyResponse {

    @Schema(description = "일상 Id", example = "1")
    private Long dailyId;

    @Schema(description = "일상 카테고리", example = "SUCCESS")
    private String dailyCate;

    @Schema(description = "스코어", example = "10")
    private int score;

    private LocalDateTime regTime;

    public static GetDetailDailyResponse of(
            Long dailyId, String dailyCate, int score, LocalDateTime regTime) {
        return new GetDetailDailyResponse(dailyId, dailyCate, score, regTime);
    }
}
