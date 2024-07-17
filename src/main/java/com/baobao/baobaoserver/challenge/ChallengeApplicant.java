package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_challenge_applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private Member applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private Challenge challenge;
}
