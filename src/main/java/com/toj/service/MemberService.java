package com.toj.service;

import com.toj.config.jwt.TokenProvider;
import com.toj.dto.member.UserLoginDto;
import com.toj.entity.Member;
import com.toj.exception.NotFoundException;
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
    public Map<String, Object> login(UserLoginDto userLoginDto) {
        Long memberId = findOrCreateMember(userLoginDto);
        String token = tokenProvider.generateToken(memberId);
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("token", token);
        map.put("memberId", memberId);
        return map;
    }

    private Long findOrCreateMember(UserLoginDto userLoginDto) {
        return memberRepository.findByEmail(userLoginDto.getEmail())
                .map(Member::getId)
                .orElseGet(() -> createMember(userLoginDto));
    }

    @Transactional
    private Long createMember(UserLoginDto userLoginDto) {
        Member member = new Member(userLoginDto.getEmail(), userLoginDto.getName(), makeNickName());
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
                .orElseThrow(() -> new NotFoundException("존재하지 않은 회원입니다."));
    }

    public Long deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("존재하지 않은 회원입니다."));

        memberRepository.delete(member);
        return member.getId();
    }
}
