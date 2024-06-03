package com.toj.controller;

import com.toj.dto.member.RemoveMemberDto;
import com.toj.dto.member.UserLoginDto;
import com.toj.global.model.ApiResponse;
import com.toj.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Map login(@RequestBody UserLoginDto userLoginDto) {
        return memberService.login(userLoginDto);
    }

    @DeleteMapping("/{memberId}")
    public ApiResponse<RemoveMemberDto> removeMember(@PathVariable Long memberId) {
        return ApiResponse.success(new RemoveMemberDto(memberService.deleteMember(memberId)));
    }
}
