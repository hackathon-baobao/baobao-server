package com.baobao.baobaoserver.challenge;

public record ApplicantRes(
        Long applicantId,
        String email,
        String name,
        Boolean isComplete
) {
    static public ApplicantRes of(ChallengeApplicant applicant){
        return new ApplicantRes(
                applicant.getId(),
                applicant.getApplicant().getEmail(),
                applicant.getApplicant().getName(),
                applicant.getIsComplete()
        );
    }
}
