package com.toj.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberLoginDto {

    @Schema(description = "회원 이름", example = "안이연")
    private String name;
    @Schema(description = "회원 이메일", example = "lxxyeon@gmail.com")
    private String email;
}
