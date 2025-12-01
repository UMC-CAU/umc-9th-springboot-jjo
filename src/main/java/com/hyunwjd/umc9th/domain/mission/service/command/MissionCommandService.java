package com.hyunwjd.umc9th.domain.mission.service.command;

import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionCommandService {
    MemberMission challengeMission(Long missionId, Long memberId);

    MissionResDTO.MissionDetailDTO completeMission(Long memberId, Long missionId);
}
