package com.hyunwjd.umc9th.global.apiPayload;

import com.fasterxml.jackson.annotation.*;
import com.hyunwjd.umc9th.global.apiPayload.code.BaseErrorCode;

import com.hyunwjd.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.*;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 응답 메서드 (result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    // 성공항 경우 응답 메서드 (result 미포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), null );
    }

    // 실패한 경우 응답 메서드 (result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
