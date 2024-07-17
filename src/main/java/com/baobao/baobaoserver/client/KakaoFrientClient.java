package com.baobao.baobaoserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoFriendClient",url = "https://kapi.kakao.com")
public interface KakaoFrientClient {
    @GetMapping("/v1/api/talk/friends")
    ElementsWrapper getFriend(@RequestHeader("Authorization") String accessToken);
}
