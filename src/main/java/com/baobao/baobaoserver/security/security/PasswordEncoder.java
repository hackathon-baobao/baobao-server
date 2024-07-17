package com.baobao.baobaoserver.security.security;

import com.baobao.baobaoserver.common.exception.CustomException;
import com.baobao.baobaoserver.common.exception.GlobalExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
@Slf4j
final class PasswordEncoder {

    public String encode(String rawPassword) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(rawPassword.getBytes());

            for (byte byteDatum : md.digest()) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            log.error("Encrypt Exception : " + e.getClass());
            log.error("Encrypt Exception Message : " + e.getMessage());

            throw new CustomException(GlobalExceptionCode.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean matches(String rawPassword, String encryptedPassword) {
        return encryptedPassword.equals(encode(rawPassword));
    }

}