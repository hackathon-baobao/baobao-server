package com.baobao.baobaoserver.challenge;

import com.baobao.baobaoserver.common.exception.CustomException;

public class AlreadyCompleteChallengeException extends CustomException {
    public AlreadyCompleteChallengeException() {
        super(ChallengeExceptionCode.ALREADY_IS_COMPLETE);
    }
}
