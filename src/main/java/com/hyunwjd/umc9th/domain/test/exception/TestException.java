package com.hyunwjd.umc9th.domain.test.exception;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code){
        super(code); //
    }
}
