package com.baobao.baobaoserver.member;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private String kakaoAccessToken;

    private String kakaoRefreshToken;

    public void update(String email, String name, String kakaoAccessToken, String kakaoRefreshToken) {
        this.email = email;
        this.name = name;
        this.kakaoAccessToken = kakaoAccessToken;
        this.kakaoRefreshToken = kakaoRefreshToken;
    }
}
