package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeApplicantRepository extends JpaRepository<ChallengeApplicant, Long> {
    List<ChallengeApplicant> findByChallenge(Challenge challenge);
    ChallengeApplicant findByChallengeAndApplicant(Challenge challenge, Member applicant);
}
