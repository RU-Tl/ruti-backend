package com.toj.controller;

import com.toj.dto.member.*;
import com.toj.entity.Member;
import com.toj.global.model.ApiResponse;
import com.toj.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "member", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "로그인 API",
            description = "로그인 및 회원가입을 수행합니다."
    )
    @PostMapping("/login")
    public Map login(@RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @Operation(
            summary = "회원 탈퇴 API",
            description = "회원정보를 삭제합니다."
    )
    @DeleteMapping("/{memberId}")
    public ApiResponse<RemoveMemberResponse> removeMember(@PathVariable Long memberId) {
        return ApiResponse.success(new RemoveMemberResponse(memberService.deleteMember(memberId)));
    }

    @Operation(
            summary = "마이페이지 조회 API",
            description = "마이페이지에서 사용자 정보를 조회합니다."
    )
    @GetMapping("/myPage/{memberId}")
    public ApiResponse<MyPageResponse> getMyPage(@PathVariable Long memberId) {
        Member member = memberService.findMemberInfo(memberId);
        return ApiResponse.success(new MyPageResponse(member.getNickname(), member.getGrade().getValue(), member.getTotalScore()));
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
