package com.hyunwjd.umc9th.domain.store.exception;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
