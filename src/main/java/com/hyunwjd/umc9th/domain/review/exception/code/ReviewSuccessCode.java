package com.hyunwjd.umc9th.domain.review.exception.code;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.NOT_FOUND,
            "REVIEW200",
            "OK"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
