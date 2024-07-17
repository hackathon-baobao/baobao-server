package com.baobao.baobaoserver.auth.service;

import com.baobao.baobaoserver.client.KakaoAuthClient;
import com.baobao.baobaoserver.client.KakaoTokenResponse;
import com.baobao.baobaoserver.client.KakaoUserClient;
import com.baobao.baobaoserver.client.KakaoUserInfoResponse;
import com.baobao.baobaoserver.member.Member;
import com.baobao.baobaoserver.member.MemberRepository;
import com.baobao.baobaoserver.member.MemberRole;
import com.baobao.baobaoserver.security.security.jwt.Token;
import com.baobao.baobaoserver.security.security.jwt.TokenIssuer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;
    private final MemberRepository memberRepository;
    private final TokenIssuer tokenIssuer;

    @Transactional(rollbackFor = Exception.class)
    public Token login(String code){
        KakaoTokenResponse kakaoToken = kakaoAuthClient.generateToken("authorization_code", "f1a7fecc6abc2f764593468b8ff349fc", "http://localhost:3000/callback", code);
        KakaoUserInfoResponse kakaoUser = kakaoUserClient.getKakaoUser("Bearer "+kakaoToken.access_token());
        log.info("{}",kakaoUser);
        Member member = memberRepository.findByEmail(kakaoUser.kakao_account().email());

        if (member == null) {
            member = Member.builder()
                    .email(kakaoUser.kakao_account().email())
                    .name(kakaoUser.kakao_account().profile().nickname())
                    .role(MemberRole.MEMBER)
                    .kakaoAccessToken(kakaoToken.access_token())
                    .kakaoRefreshToken(kakaoToken.refresh_token())
                    .build();
            memberRepository.save(member);
        } else {
            member.update(
                    kakaoUser.kakao_account().email(),
                    kakaoUser.kakao_account().profile().nickname(),
                    kakaoToken.access_token(),
                    kakaoToken.refresh_token());
            memberRepository.save(member);
        }
        log.info("{},{}",member.getEmail(), member.getRole());
        return tokenIssuer.issueToken(member.getEmail(), member.getRole());
    }
}
