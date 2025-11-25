package com.hyunwjd.umc9th.domain.member.exception.code;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;
import lombok.*;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
            "MEMBER200-1",
            "사용자 정보를 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
