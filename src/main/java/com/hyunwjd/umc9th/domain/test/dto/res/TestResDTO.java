package com.hyunwjd.umc9th.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testString;
    }

    // 추가된 응답   DTO
    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }

}
