package com.toj.config.filter;

import com.toj.config.jwt.TokenProvider;
import com.toj.entity.Member;
import com.toj.exception.NotFoundException;
import com.toj.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        String token = getAccessToken(authorizationHeader);

        if (ObjectUtils.isEmpty(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String email = tokenProvider.getUserEmail(token);
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("You are an unregistered user."));

        if (findMember.getEmail().equals(email)) {
            return true;
        }
        throw new IllegalAccessException("Invalid Token");
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
