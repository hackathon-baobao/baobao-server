package com.baobao.baobaoserver.tree;

import com.baobao.baobaoserver.common.exception.CustomException;
import com.baobao.baobaoserver.common.exception.GlobalExceptionCode;

public class LessPointException extends CustomException {
    public LessPointException() {
        super(GlobalExceptionCode.LESS_POINT);
    }
}
