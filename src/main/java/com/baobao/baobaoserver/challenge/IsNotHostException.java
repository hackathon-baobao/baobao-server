package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.common.exception.CustomException;

public class IsNotHostException extends CustomException {
    public IsNotHostException() {
        super(ChallengeExceptionCode.IS_NOT_HOST);
    }
}
