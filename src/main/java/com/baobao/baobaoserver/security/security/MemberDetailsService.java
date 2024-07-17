package com.baobao.baobaoserver.security.security;

import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberService.getByEmail(email);
        return MemberDetails.of(member);
    }
}