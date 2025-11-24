package com.hyunwjd.umc9th.domain.member.controller;

import com.hyunwjd.umc9th.domain.member.converter.MemberConverter;
import com.hyunwjd.umc9th.domain.member.dto.req.MemberReqDTO;
import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.hyunwjd.umc9th.domain.member.service.command.MemberCommandService;
import com.hyunwjd.umc9th.domain.member.service.query.MemberQueryService;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;
import com.hyunwjd.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;


    //회원 미션 달성도 조회
    @PostMapping("/{memberId}/missions/stats")
    public ApiResponse<MemberResDTO.MemberMissionStats> getMemberMissionStats(
            // 나중에 토큰 사용 시 @AuthenticationPrincipal 등으로 교체 예정
            @PathVariable Long memberId
    ) {

        // 1) 서비스에서 DTO 가져오기
        MemberResDTO.MemberMissionStats statsDTO =
                memberQueryService.getMemberMissionStats(memberId);

        // 2) 응답 래핑
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                statsDTO
        );
    }


    //회원가입
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
