package com.hyunwjd.umc9th.domain.member.exception;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
