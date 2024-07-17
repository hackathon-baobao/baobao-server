package com.baobao.baobaoserver.challenge;

import java.time.LocalDate;

public record ChallengeDescriptionRes(
        Long id,
        String title,
        String description,
        LocalDate startAt,
        LocalDate endAt,
        String hostId,
        String hostName
) {
    public static ChallengeDescriptionRes of(Challenge challenge){
        return new ChallengeDescriptionRes(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getStartAt(),
                challenge.getEndAt(),
                challenge.getHost().getEmail(),
                challenge.getHost().getName()
        );
    }
}