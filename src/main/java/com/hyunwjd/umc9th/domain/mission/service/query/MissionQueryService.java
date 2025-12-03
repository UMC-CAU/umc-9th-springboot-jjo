package com.hyunwjd.umc9th.domain.mission.service.query;

import com.hyunwjd.umc9th.domain.mission.dto.res.MissionResDTO;
import com.hyunwjd.umc9th.domain.mission.enums.MissionStatusFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    // 내가 참여한 미션 조회
    Page<MissionResDTO.MissionPreviewDTO> getMyMissions(
            Long memberId,
            MissionStatusFilter status,
            Pageable pageable
    );
}
