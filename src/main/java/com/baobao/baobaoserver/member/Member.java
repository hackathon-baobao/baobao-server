package com.baobao.baobaoserver.member;

import com.baobao.baobaoserver.tree.AnimalState;
import com.baobao.baobaoserver.tree.TreeLevel;
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

    private String treeKind;

    @Enumerated(EnumType.STRING)
    private AnimalState bird;

    @Enumerated(EnumType.STRING)
    private AnimalState squirrel;

    @Enumerated(EnumType.STRING)
    private AnimalState bunny;

    @Enumerated(EnumType.STRING)
    private AnimalState deer;

    private Long height;

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

    public void minusPoint(Long point){
        this.point -= point;
    }

    public void addHeight(Long height){
        this.height += height;
        this.point -= height;
    }

    public void changeTree(String tree){
        this.treeKind = tree;
        this.point-=1000;
    }

    public void setBird(AnimalState bird) {
        this.bird = bird;
    }

    public void setSquirrel(AnimalState squirrel) {
        this.squirrel = squirrel;
    }

    public void setBunny(AnimalState bunny) {
        this.bunny = bunny;
    }

    public void setDeer(AnimalState deer) {
        this.deer = deer;
    }
}
