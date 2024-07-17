package com.baobao.baobaoserver.security.security.jwt;

import com.baobao.baobaoserver.common.exception.CustomException;
import com.baobao.baobaoserver.common.exception.GlobalExceptionCode;

class WrongTokenTypeException extends CustomException {

    static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(GlobalExceptionCode.WRONG_TOKEN_TYPE);
    }

}