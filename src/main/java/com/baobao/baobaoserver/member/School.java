package com.baobao.baobaoserver.member;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_school")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class School {
    @Id
    private String name;
}
