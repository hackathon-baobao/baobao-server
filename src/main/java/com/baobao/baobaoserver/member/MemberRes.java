package com.baobao.baobaoserver.member;

public record MemberRes(
        String email,
        String name
) {
    static public MemberRes of(Member member){
        return new MemberRes(member.getEmail(),member.getName());
    }
}
