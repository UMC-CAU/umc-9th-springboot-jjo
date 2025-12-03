package com.hyunwjd.umc9th.domain.mission.converter;

import com.hyunwjd.umc9th.domain.mission.dto.res.MissionResDTO;
import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class MissionConverter {

    // 1) MemberMission → MissionPreviewDTO
    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission mm) {

        Mission mission = mm.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .completeCondition(mission.getCompleteCondition())
                .storeName(store.getName())
                .isComplete(mm.isCompleted())
                .completedAt(mm.getCompletedAt())
                .createdAt(mm.getCreatedAt())
                .updatedAt(mm.getUpdatedAt())
                .build();
    }


    //2) Page<MemberMission> → MissionPreviewListDTO
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<MemberMission> page
    ) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(
                        page.getContent().stream()
                                .map(MissionConverter::toMissionPreviewDTO)
                                .collect(Collectors.toList())
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }


    // MemberMission → MissionDetailDTO (진행 완료 API 결과)
    public static MissionResDTO.MissionDetailDTO toMissionDetailDTO(MemberMission mm) {

        Mission mission = mm.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MissionDetailDTO.builder()
                .missionId(mission.getId())
                .completeCondition(mission.getCompleteCondition())
                .storeName(store.getName())
                .isCompleted(mm.isCompleted())
                .completedAt(mm.getCompletedAt())
                .createdAt(mm.getCreatedAt())
                .updatedAt(mm.getUpdatedAt())
                .build();
    }
}
