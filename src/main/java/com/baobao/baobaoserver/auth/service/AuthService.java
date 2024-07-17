package com.baobao.baobaoserver.auth.service;

import com.baobao.baobaoserver.client.KakaoAccount;
import com.baobao.baobaoserver.client.KakaoAuthClient;
import com.baobao.baobaoserver.client.KakaoUserClient;
import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberRepository;
import com.baobao.baobaoserver.member.MemberRole;
import com.baobao.baobaoserver.security.security.jwt.Token;
import com.baobao.baobaoserver.security.security.jwt.TokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;
    private final MemberRepository memberRepository;
    private final TokenIssuer tokenIssuer;

    @Transactional(rollbackFor = Exception.class)
    public Token login(String code){

        Token kakaoToken = kakaoAuthClient.generateToken("authorization_code", "f1a7fecc6abc2f764593468b8ff349fc", "http://localhost:3000/callback", code);
        KakaoAccount kakaoUser = kakaoUserClient.getKakaoUser(kakaoToken.accessToken(), "[kakao_account.profile" + "," + "kakao_account.name" + "," + "kakao_account.email]");
        Member member = memberRepository.findByEmail(kakaoUser.kakaoAccount().email());

        if (member == null) {
            member = Member.builder()
                    .email(kakaoUser.kakaoAccount().email())
                    .name(kakaoUser.kakaoAccount().profile().nickname())
                    .role(MemberRole.MEMBER)
                    .kakaoAccessToken(kakaoToken.accessToken())
                    .kakaoRefreshToken(kakaoToken.refreshToken())
                    .build();
            memberRepository.save(member);
        } else {
            member.update(
                    kakaoUser.kakaoAccount().email(),
                    kakaoUser.kakaoAccount().profile().nickname(),
                    kakaoToken.accessToken(),
                    kakaoToken.refreshToken());
            memberRepository.save(member);
        }

        return tokenIssuer.issueToken(member.getEmail(), member.getRole());
    }
}
