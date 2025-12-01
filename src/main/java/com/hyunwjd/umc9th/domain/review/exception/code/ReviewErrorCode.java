package com.hyunwjd.umc9th.domain.review.exception.code;
import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
