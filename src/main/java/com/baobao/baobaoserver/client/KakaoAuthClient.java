package com.baobao.baobaoserver.client;

import com.baobao.baobaoserver.security.security.jwt.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoAuthClient",url = "https://kauth.kakao.com")
public interface KakaoAuthClient {
    @PostMapping("/oauth/token")
    Token generateToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String code);
}
