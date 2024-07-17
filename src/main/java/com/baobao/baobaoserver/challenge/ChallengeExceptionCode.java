package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.common.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ChallengeExceptionCode implements ExceptionCode {
    IS_NOT_HOST(HttpStatus.FORBIDDEN, "개최자가 아님"),
    ALREADY_IS_COMPLETE(HttpStatus.BAD_REQUEST, "이미 성공한 챌린지");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getExceptionName() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
