package com.hyunwjd.umc9th.domain.review.exception;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
  public ReviewException(BaseErrorCode code) {
    super(code);
  }
}
