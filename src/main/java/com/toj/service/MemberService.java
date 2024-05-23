package com.toj.service;

import com.toj.config.jwt.TokenProvider;
import com.toj.dto.member.UserLoginDto;
import com.toj.entity.Member;
import com.toj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    public Map<String, Object> login(UserLoginDto userLoginDto) {
        Member member = findOrCreateMember(userLoginDto);
        String token = tokenProvider.generateToken(member.getEmail());
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("token", token);
        map.put("memberId", member.getId());

        return map;
    }

    private Member findOrCreateMember(UserLoginDto userLoginDto) {
        return memberRepository.findByEmail(userLoginDto.getEmail())
                .orElseGet(() -> createMember(userLoginDto));
    }

    private Member createMember(UserLoginDto userLoginDto) {
        Member member = new Member(userLoginDto.getName(), userLoginDto.getEmail(), makeNickName());
        memberRepository.save(member);

        return member;
    }

    private String makeNickName() {
        return "RoutineMaster" + LocalDateTime.now();
    }
}
