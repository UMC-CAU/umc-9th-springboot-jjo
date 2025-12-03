package com.hyunwjd.umc9th.domain.mission.exception.code;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾지 못했습니다.")
    ,

    MISSION_ALREADY_IN_PROGRESS(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "이미 도전 중인 미션입니다."),

    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "이미 도전 완료된 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
