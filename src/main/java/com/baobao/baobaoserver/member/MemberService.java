package com.baobao.baobaoserver.member;

import com.baobao.baobaoserver.common.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberSessionHolder sessionHolder;

    public Member getByEmail(String email){
        return memberRepository.findById(email).get();
    }

    public Response appendSchool(String school){
        Member curMember = sessionHolder.current();
        curMember.updateSchool(school);
        memberRepository.save(curMember);
        return Response.ok("학교 수집 성공");
    }
}
