package com.hyunwjd.umc9th.domain.auth.controller;

import com.hyunwjd.umc9th.domain.member.dto.req.MemberReqDTO;
import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.hyunwjd.umc9th.domain.member.service.command.MemberCommandService;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinDTO> signup(
            @RequestBody MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.FOUND,
                memberCommandService.signup(dto)
        );
    }
}
