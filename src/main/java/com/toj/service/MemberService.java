package com.toj.service;

import com.toj.config.jwt.TokenProvider;
import com.toj.dto.member.MemberLoginDto;
import com.toj.entity.Member;
import com.toj.global.code.ErrorCode;
import com.toj.global.error.exception.ForbiddenException;
import com.toj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    public Map<String, Object> login(MemberLoginDto memberLoginDto) {
        Long memberId = findOrCreateMember(memberLoginDto);
        String token = tokenProvider.generateToken(memberId);
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("token", token);
        map.put("memberId", memberId);
        return map;
    }

    private Long findOrCreateMember(MemberLoginDto memberLoginDto) {
        return memberRepository.findByEmail(memberLoginDto.getEmail())
                .map(Member::getId)
                .orElseGet(() -> createMember(memberLoginDto));
    }

    @Transactional
    private Long createMember(MemberLoginDto memberLoginDto) {
        Member member = new Member(memberLoginDto.getEmail(), memberLoginDto.getName(), makeNickName());
        Long id = memberRepository.save(member).getId();

        return id;
    }

    private String makeNickName()  {
        Long max = memberRepository.findMaxMemberId()
                .orElseGet(() -> 0L);
        return "루꼬미" + (max+1);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() ->new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION));
    }

    public Long deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION));

        memberRepository.delete(member);
        return member.getId();
    }

    public Member findMemberInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ForbiddenException(ErrorCode.FORBIDDEN_EXCEPTION));
    }
}
