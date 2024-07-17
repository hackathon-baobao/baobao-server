package com.baobao.baobaoserver.security.security.jwt;

import com.baobao.baobaoserver.member.MemberRole;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenIssuer {
    private final JwtProperties jwtProperties;

    public Token issueToken(String email, MemberRole role){
        return new Token(
                issueAccessToken(email,role),
                issueRefreshToken(email,role)
        );
    }

    public String issueAccessToken(String email, MemberRole memberRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(email)
                .claim("authority", memberRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtProperties.getAccessExpire())))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getAccessKey())
                .compact();
    }

    public String issueRefreshToken(String email, MemberRole memberRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(email)
                .claim("authority", memberRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtProperties.getRefreshExpire())))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }
}
