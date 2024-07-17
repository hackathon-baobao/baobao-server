package com.baobao.baobaoserver.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum GlobalExceptionCode implements ExceptionCode {
    PARAMETER_NOT_FOUND(HttpStatus.BAD_REQUEST, "잘못된 파라미터"),
    PARAMETER_NOT_VALID(HttpStatus.BAD_REQUEST, "잘못된 파라미터"),
    ACCESS_DENIED(FORBIDDEN, "Access denied"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "InternalServerException occurred"),
    MEDIA_TYPE_NOT_SUPPORTED(UNSUPPORTED_MEDIA_TYPE, "Http media type is not supported"),
    MEDIA_TYPE_MISS_MATCHED(HttpStatus.BAD_REQUEST, "잘못된 미디어 값"),
    EMPTY_FILE(BAD_REQUEST, "File is empty"),
    METHOD_NOT_SUPPORTED(METHOD_NOT_ALLOWED, "Http method is not supported"),
    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰"),
    INVALID_ROLE(FORBIDDEN, "유효하지 않은 권한"),
    TOKEN_NOT_PROVIDED(BAD_REQUEST, "잘못된 토큰"),
    TOKEN_EXPIRED(UNAUTHORIZED, "만료된 토큰"),
    WRONG_TOKEN_TYPE(BAD_REQUEST, "Check your token type"),
    JWT_SIGNATURE_NOT_MATCHED(BAD_REQUEST, "JWT signature doesn't matched"),
    MALFORMED_JWT(BAD_REQUEST, "Jwt is malformed"),
    UNSUPPORTED_JWT(BAD_REQUEST, "Jwt is unsupported"),
    ILLEGAL_ARGUMENT(BAD_REQUEST, "IllegalArgumentException occurred"),
    EXPIRED_JWT(UNAUTHORIZED, "Jwt is expired"),
    UNABLE_TO_SEND_EMAIL(FORBIDDEN,"Unable to send email"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호"),
    ENDPOINT_NOT_FOUND(NOT_FOUND, "엔드포인트를 찾을 수 없음");

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
