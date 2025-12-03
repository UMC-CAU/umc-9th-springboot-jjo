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
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    //미션 도전 API
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

    //내가 진행중인 미션 조회 API -> MissionStatusFilter를 적용했기 때문에 진행완료한 미션 조회도 가능함
    @GetMapping("/my-page/missions")
    public ApiResponse<Page<MissionResDTO.MissionPreviewDTO>> getMyMissions(
            @PathVariable Long memberId,
        @RequestParam(defaultValue = "ALL") MissionStatusFilter status,
            Integer page
    ) {


        Pageable pageable = PageRequest.of(page - 1, 10);  // 한 페이지 10개
        Page<MissionResDTO.MissionPreviewDTO> result = missionQueryService.getMyMissions(memberId, status, pageable);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    //미션 완료 API
    // 진행 중인 미션 완료 처리
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionDetailDTO> completeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId   // 나중엔 토큰에서 꺼내기
    ) {
        MissionResDTO.MissionDetailDTO result =
                missionCommandService.completeMission(memberId, missionId);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}
