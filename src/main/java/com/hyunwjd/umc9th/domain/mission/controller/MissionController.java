package com.hyunwjd.umc9th.domain.mission.controller;

import com.hyunwjd.umc9th.domain.mission.converter.MemberMissionConverter;
import com.hyunwjd.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.hyunwjd.umc9th.domain.mission.dto.res.MissionResDTO;
import com.hyunwjd.umc9th.domain.mission.enums.MissionStatusFilter;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.mission.service.command.MissionCommandService;
import com.hyunwjd.umc9th.domain.mission.service.query.MissionQueryService;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;
import com.hyunwjd.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MemberMissionResDTO.ChallengeResult> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId   // 나중에 인증 붙이면 토큰에서 꺼내기
    ) {
        MemberMission mm = missionCommandService.challengeMission(missionId, memberId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MemberMissionConverter.toChallengeResult(mm)
        );
    }


    @GetMapping("/my-page/missions")
    public ApiResponse<Page<MissionResDTO>> getMyMissions(
            @PathVariable Long memberId,
        @RequestParam(defaultValue = "ALL") MissionStatusFilter status,
            Pageable pageable
    ) {
        Page<MissionResDTO> page = missionQueryService.getMyMissions(memberId, status, pageable);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                page
        );
    }

}
