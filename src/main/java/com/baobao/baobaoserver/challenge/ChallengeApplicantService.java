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
public class ChallengeApplicantService {
    private final ChallengeApplicantRepository challengeApplicantRepository;
    private final MemberSessionHolder memberSessionHolder;
    private final ChallengeRepository challengeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Response append(Long challengeId){
        Member curMember = memberSessionHolder.current();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        challengeApplicantRepository.save(
                ChallengeApplicant.builder()
                        .isComplete(Boolean.FALSE)
                        .applicant(curMember)
                        .challenge(challenge)
                        .build());
        return Response.ok("챌린지 지원 성공");
    }

    @Transactional(rollbackFor = Exception.class)
    public Response delete(Long challengeId){
        Member curMember = memberSessionHolder.current();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        ChallengeApplicant applicant = challengeApplicantRepository
                .findByChallengeAndApplicant(challenge, curMember);
        challengeApplicantRepository.delete(applicant);
        return Response.ok("챌린지 지원 취소 성공");
    }

    public ResponseData<List<ApplicantRes>> read(Long challengeId){
        Member curMember = memberSessionHolder.current();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        if(challenge.getHost().equals(curMember)) throw new IsNotHostException();
        List<ChallengeApplicant> applicants = challengeApplicantRepository.findByChallenge(challenge);
        return ResponseData.ok("챌린지 불러오기 성공", applicants.stream()
                .map(ApplicantRes::of).toList());
    }

    public Response compete(Long id){
        Member curMember = memberSessionHolder.current();
        ChallengeApplicant applicant = challengeApplicantRepository.findById(id).get();
        if(applicant.getChallenge().getHost().equals(curMember)) throw new IsNotHostException();
        if(applicant.getIsComplete()) throw new AlreadyCompleteChallengeException();
        applicant.updateIsComplete(Boolean.TRUE);
        challengeApplicantRepository.save(applicant);
        applicant.getApplicant().addPoint(100L);
        return Response.ok("챌린지 성공 처리");
    }
}
