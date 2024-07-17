package com.baobao.baobaoserver.tree;

import com.baobao.baobaoserver.common.exception.CustomException;
import com.baobao.baobaoserver.common.exception.GlobalExceptionCode;

public class HaveNotAnimalException extends CustomException {
    public HaveNotAnimalException() {
        super(GlobalExceptionCode.HAVE_NOT_ANIMAL);
    }
}
