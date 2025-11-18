package com.hyunwjd.umc9th.domain.member.controller;

import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class MemberController {
    @GetMapping("/{memberId}/missions/stats")
    public ApiResponse<MemberResDTO.MemberMissionStats> getMemberMissionStats(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(

        );
    }

}
