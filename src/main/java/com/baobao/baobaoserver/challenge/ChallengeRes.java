package com.baobao.baobaoserver.challenge;

import java.time.LocalDate;

public record ChallengeRes(
        Long id,
        String title,
        LocalDate startAt,
        LocalDate endAt,
        String hostId,
        String hostName
) {
    public static ChallengeRes of(Challenge challenge){
        return new ChallengeRes(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getStartAt(),
                challenge.getEndAt(),
                challenge.getHost().getEmail(),
                challenge.getHost().getName()
        );
    }
}