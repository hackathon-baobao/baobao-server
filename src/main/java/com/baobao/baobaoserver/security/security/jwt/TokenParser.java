package com.baobao.baobaoserver.security.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.invoke.WrongMethodTypeException;

@Component
@RequiredArgsConstructor
class TokenParser {

    private final JwtProperties jwtProperties;

    public String getSubjectFromRefreshToken(final String refreshToken) {
        return getSubject(refreshToken, JwtType.REFRESH);
    }

    public String getSubjectFromAccessToken(final String accessToken) {
        return getSubject(accessToken, JwtType.ACCESS);
    }

    private String getSubject(final String token, final JwtType jwtType) {
        final String key = jwtType==JwtType.ACCESS ? jwtProperties.getAccessKey() : jwtProperties.getSecretKey();

        final Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(extractToken(token));

        this.isWrongType(jwsClaims, jwtType);

        return jwsClaims.getBody().getSubject();
    }

    private void isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        if(!(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()))) {
            throw new WrongMethodTypeException();
        }
    }

    private String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

}
