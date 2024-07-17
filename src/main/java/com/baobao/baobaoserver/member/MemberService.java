package com.baobao.baobaoserver.member;

import com.baobao.baobaoserver.client.ElementsWrapper;
import com.baobao.baobaoserver.client.KakaoFrientClient;
import com.baobao.baobaoserver.client.ProfileRecord;
import com.baobao.baobaoserver.common.Response;
import com.baobao.baobaoserver.common.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberSessionHolder sessionHolder;
    private final KakaoFrientClient kakaoFrientClient;

    public Member getByEmail(String email){
        return memberRepository.findById(email).get();
    }

    public Response appendSchool(String school){
        Member curMember = sessionHolder.current();
        curMember.updateSchool(school);
        memberRepository.save(curMember);
        return Response.ok("학교 수집 성공");
    }

    public ResponseData<List<SchoolHeight>> getSchoolHeightRanks() {
        return ResponseData.ok("학교 랭킹 조회 성공",memberRepository.findSchoolHeights());
    }

    public ResponseData<List<KakaoHeight>> getKakaoHeightRanks(){
        Member curMember = sessionHolder.current();
        ElementsWrapper elementsWrapper = kakaoFrientClient.getFriend("Bearer "+curMember.getKakaoAccessToken());
        List<String> names = elementsWrapper.elements().stream().map(ProfileRecord::profile_nickname).toList();
        List<Member> members = memberRepository.findByNameInOrderByPointDesc(names);
        members.add(curMember);
        List<KakaoHeight> result = members.stream().map(m->new KakaoHeight(m.getEmail(),m.getName(),m.getHeight())).toList();
        return ResponseData.ok("카카오 랭킹 조회 성공", result);
    }

    public ResponseData<Long> getPoint(){
        return ResponseData.ok("포인트 조회 성공",sessionHolder.current().getPoint());
    }
}
