package com.baobao.baobaoserver.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getByEmail(String email){
        return memberRepository.findById(email).get();
    }
}
