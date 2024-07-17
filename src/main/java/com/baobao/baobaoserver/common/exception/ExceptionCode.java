package com.baobao.baobaoserver.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
    HttpStatus getStatus();
    String getExceptionName();
    String getMessage();
}
