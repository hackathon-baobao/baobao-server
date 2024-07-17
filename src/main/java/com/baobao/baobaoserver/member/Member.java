package com.baobao.baobaoserver.member;

import jakarta.persistence.*;
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

    private String school;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private String kakaoAccessToken;

    private String kakaoRefreshToken;

    private Long point;

    public void update(String email, String name, String kakaoAccessToken, String kakaoRefreshToken) {
        this.email = email;
        this.name = name;
        this.kakaoAccessToken = kakaoAccessToken;
        this.kakaoRefreshToken = kakaoRefreshToken;
    }

    public void updateSchool(String school){
        this.school = school;
    }

    public void addPoint(Long point){
        this.point += point;
    }
}
