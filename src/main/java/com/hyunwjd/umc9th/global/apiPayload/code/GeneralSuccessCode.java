package com.hyunwjd.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청이 성공적으로 처리되었습니다."), //일반적으로 POST 요청 또는 일부 PUT 요청 후에 전송되는 응답
    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스가 성공적으로 생성되었습니다."), //다른 프로세스에서 처리 또는 서버가 요청을 다루고 있거나 배치 프로세스를 하고 있는 경우를 위함
    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 접수되었습니다."), // 반환된 메타 정보가 원본 서버에서 사용할 수 있는 것과 정확히 동일하지는 않지만 로컬 또는 타사 복사본에서 수집되었음을 의미
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204_1",
            "응답할 콘텐츠가 없습니다."); //이 요청에 대해 보낼 내용은 없지만 헤더가 유용할 수 있습니다. 사용자-에이전트는 이 리소스에 대한 캐시 된 헤더를 새 헤더를 업데이트할 수 있다.

    private final HttpStatus status;
    private final String code;
    private final String message;
}
