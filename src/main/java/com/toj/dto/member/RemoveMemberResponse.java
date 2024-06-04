package com.toj.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "회원 탈퇴 DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveMemberResponse {

    @Schema(description = "회원 ID", example = "1")
    Long memberId;
}
