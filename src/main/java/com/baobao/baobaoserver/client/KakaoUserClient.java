package com.baobao.baobaoserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "kakaoUserClient", url = "https://kapi.kakao.com/v2/user")
public interface KakaoUserClient {
    @GetMapping("/me")
    KakaoAccount getKakaoUser(
            @RequestHeader("Authorization") String accessToken,
            @RequestParam("property_keys") String propertyKeys);
}
