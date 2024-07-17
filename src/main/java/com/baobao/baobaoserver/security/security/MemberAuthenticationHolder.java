package com.baobao.baobaoserver.security.security;

import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberSessionHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberAuthenticationHolder implements MemberSessionHolder {
    @Override
    public Member current() {
        return ((MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }
}
