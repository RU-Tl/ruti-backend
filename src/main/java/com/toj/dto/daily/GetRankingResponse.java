package com.toj.dto.daily;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Schema(description = "랭킹 조회 DTO")
public class GetRankingResponse {

    @Schema(description = "유저 Id", example = "1")
    private Long memberId;

    @Schema(description = "유저 닉네임", example = "루티는누군가요")
    private String nickname;

    @Schema(description = "총합 스코어", example = "320")
    private Integer totalScore;

    @Schema(description = "순위", example = "1")
    private Integer ranking;

    public GetRankingResponse(Integer ranking) {
        this.ranking = ranking;
    }

    @QueryProjection
    public GetRankingResponse(Long memberId, String nickname, Integer totalScore) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.totalScore = totalScore;
    }
}
