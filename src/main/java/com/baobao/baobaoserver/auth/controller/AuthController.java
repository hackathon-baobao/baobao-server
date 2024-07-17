package com.baobao.baobaoserver.auth.controller;

import com.baobao.baobaoserver.auth.service.AuthService;
import com.baobao.baobaoserver.common.ResponseData;
import com.baobao.baobaoserver.security.security.jwt.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseData<Token> login(@RequestParam String code){
        return ResponseData.ok("카카오 로그인 성공", authService.login(code));
    }
}
