package com.toj.controller;

import com.toj.dto.member.MyPageDto;
import com.toj.dto.member.RemoveMemberDto;
import com.toj.dto.member.MemberLoginDto;
import com.toj.dto.member.UpdateNicknameResponse;
import com.toj.entity.Member;
import com.toj.global.model.ApiResponse;
import com.toj.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Map login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberService.login(memberLoginDto);
    }

    @DeleteMapping("/{memberId}")
    public ApiResponse<RemoveMemberDto> removeMember(@PathVariable Long memberId) {
        return ApiResponse.success(new RemoveMemberDto(memberService.deleteMember(memberId)));
    }

    @GetMapping("/myPage/{memberId}")
    public ApiResponse<MyPageDto> getMyPage(@PathVariable Long memberId) {
        Member member = memberService.findMemberInfo(memberId);
        return ApiResponse.success(new MyPageDto(member.getNickname(), member.getGrade().getValue()));
    }

    @Operation(
            summary = "닉네임 변경 API",
            description = "마이페이지에서 사용자 닉네임을 변경합니다."
    )
    @PostMapping("/nickname/{memberId}")
    public ApiResponse<UpdateNicknameResponse> updateNickname(
            @PathVariable Long memberId, @RequestParam String nickname) {

        return ApiResponse.success(
                memberService.updateNickname(memberId, nickname)
        );
    }

}
