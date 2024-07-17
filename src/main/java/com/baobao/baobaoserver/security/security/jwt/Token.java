package com.baobao.baobaoserver.security.security.jwt;

public record Token(
        String accessToken,
        String refreshToken
) {
}
