package com.toj.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateNicknameResponse {

    @Schema(description = "유저 ID", example = "1")
    private Long memberId;

    @Schema(description = "유저 닉네임", example = "까만 까마귀")
    private String nickname;

    public static UpdateNicknameResponse of(Long memberId, String nickname) {
        return new UpdateNicknameResponse(memberId, nickname);
    }
}
