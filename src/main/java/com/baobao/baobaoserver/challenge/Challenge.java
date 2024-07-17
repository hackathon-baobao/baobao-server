package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_challenge")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate startAt;

    private LocalDate endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private Member host;
}
