package com.baobao.baobaoserver.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    MEMBER("ROLE_MEMBER"),
    ADMIN("ROLE_ADMIN");
    private final String role;

    public static MemberRole toEnum(String role) {
        return Arrays.stream(values())
                .filter(value -> Objects.equals(value.role, "ROLE_"+role))
                .findAny()
                .orElse(null);
    }
}
