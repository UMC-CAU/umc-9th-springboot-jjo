package com.hyunwjd.umc9th.domain.member.converter;

import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

     //Member 엔티티 -> MemberInfo DTO 변환
    public static MemberResDTO.MemberInfo toMemberInfo(Member member) {
        return MemberResDTO.MemberInfo.builder()
                .memberId(member.getId())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .point(member.getPoint())
                .build();
    }

     //MemberMission 엔티티 한 개 -> MemberMissionList DTO 한 개 변환

    public static MemberResDTO.MemberMissionList toMemberMissionList(MemberMission mm) {
        Mission mission = mm.getMission();
        Store store = mission.getStore();

        return MemberResDTO.MemberMissionList.builder()
                .missionId(mission.getId())
                // 미션 “제목” 같은 텍스트 = complete_condition
                .complete_condition(mission.getCompleteCondition())
                // 가게 이름 = store_name
                .store_name(store.getName())        // DTO에 storeName 추가했다는 가정
                .isComplete(mm.isComplete())
                .completedAt(mm.getCompletedAt())
                .createdAt(mm.getCreatedAt())
                .updateAt(mm.getUpdatedAt())
                .build();
    }

    //MemberMission 엔티티 리스트 -> MemberMissionList DTO 리스트 변환
    public static List<MemberResDTO.MemberMissionList> toMemberMissionList(List<MemberMission> memberMissions) {
        return memberMissions.stream()
                .map(MemberConverter::toMemberMissionList)
                .collect(Collectors.toList());
    }

    //미션 달성도 통계 -> MemberMissionStats DTO 변환

    public static MemberResDTO.MemberMissionStats toMemberMissionStats(Object[] statsRaw) {
        Long remainder = (Long) statsRaw[1];          // 0~9 사이 값

        return MemberResDTO.MemberMissionStats.builder()
                .completedCount(remainder)
                .missionCompletionRate((long) remainder.intValue())  // 예: 7
                .build();
    }
}
