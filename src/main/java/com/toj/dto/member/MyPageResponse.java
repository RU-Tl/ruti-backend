package com.toj.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "마이페이지 조회 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    @Schema(description = "회원 닉네임", example = "루꼬미1")
    private String nickname;
    @Schema(description = "회원 등급", example = "루꼬미")
    private String grade;
}
