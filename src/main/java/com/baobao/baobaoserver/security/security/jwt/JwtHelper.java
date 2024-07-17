package com.baobao.baobaoserver.security.security.jwt;

import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberService;
import com.baobao.baobaoserver.security.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class JwtHelper {
    private final TokenParser tokenParser;
    private final MemberService memberService;

    void setAuthentication(final String accessToken) {
        if(accessToken != null) {
            final String email = tokenParser.getSubjectFromAccessToken(accessToken);

            final Member member = memberService.getByEmail(email);

            final Authentication authentication = getAuthentication(member);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Authentication getAuthentication(final Member member) {
        final MemberDetails details = MemberDetails.of(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }
}