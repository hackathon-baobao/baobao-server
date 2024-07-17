package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.common.Response;
import com.baobao.baobaoserver.common.ResponseData;
import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final MemberSessionHolder sessionHolder;

    @Transactional(rollbackFor = Exception.class)
    public Response append(ChallengeAppendReq req){
        Member curMember = sessionHolder.current();
        challengeRepository.save(req.toEntity(curMember));
        return Response.ok("챌린지 생성 성공");
    }

    public ResponseData<List<ChallengeRes>> read(){
        List<ChallengeRes> result = challengeRepository.findAll().stream()
                .map(ChallengeRes::of)
                .toList();
        return ResponseData.ok("챌린지 리스트 조회 성공", result);
    }

    public ResponseData<ChallengeDescriptionRes> readById(Long id){
        Challenge challenge = challengeRepository.findById(id).get();
        return ResponseData.ok("챌린지 조회 성공", ChallengeDescriptionRes.of(challenge));
    }

    @Transactional(rollbackFor = Exception.class)
    public Response delete(Long id){
        challengeRepository.deleteById(id);
        return ResponseData.ok("챌린지 삭제 성공");
    }
}
