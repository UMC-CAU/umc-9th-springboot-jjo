package com.hyunwjd.umc9th.domain.mission.service.query;
import com.hyunwjd.umc9th.domain.mission.converter.MissionConverter;
import com.hyunwjd.umc9th.domain.mission.dto.res.MissionResDTO;
import com.hyunwjd.umc9th.domain.mission.enums.MissionStatusFilter;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.mission.repository.MissionRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor

public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;

    @Override
    public Page<MissionResDTO.MissionPreviewDTO> getMyMissions(
            Long memberId,
            MissionStatusFilter status,
            Pageable pageable
    ) {
        Page<MemberMission> page = switch (status) {
            case COMPLETED   -> missionRepository.findByMemberIdAndIsCompleted(memberId, true, pageable);
            case IN_PROGRESS -> missionRepository.findByMemberIdAndIsCompleted(memberId, false, pageable);
            case ALL         -> missionRepository.findByMemberId(memberId, pageable);
            case FAILED -> null;
        };

        return Objects.requireNonNull(page).map(MissionConverter::toMissionPreviewDTO);
    }
}
