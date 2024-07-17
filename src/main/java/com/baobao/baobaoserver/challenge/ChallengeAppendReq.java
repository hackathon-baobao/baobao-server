package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.member.Member;

import java.time.LocalDate;

public record ChallengeAppendReq(
        String title,
        String description,
        LocalDate startAt,
        LocalDate endAt
) {
    public Challenge toEntity(Member member){
        return Challenge.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .host(member)
                .build();
    }
}