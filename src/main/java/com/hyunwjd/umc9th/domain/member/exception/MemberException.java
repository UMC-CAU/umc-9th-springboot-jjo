package com.hyunwjd.umc9th.domain.member.exception;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
  public MemberException(BaseErrorCode code) {
    super(code);
  }
}
