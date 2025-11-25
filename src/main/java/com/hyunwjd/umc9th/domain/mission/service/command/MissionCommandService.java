package com.hyunwjd.umc9th.domain.mission.service.command;

import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;

public interface MissionCommandService {
    MemberMission challengeMission(Long missionId, Long memberId);
}
