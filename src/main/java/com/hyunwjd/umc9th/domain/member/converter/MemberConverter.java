package com.hyunwjd.umc9th.domain.member.converter;

import com.hyunwjd.umc9th.domain.member.dto.req.MemberReqDTO;
import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import com.hyunwjd.umc9th.domain.auth.enums.Role;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
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
                .isComplete(mm.isCompleted())
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
        //Object[] row = [전체 미션 수, 완료한 미션 수]
        Long remainder = (Long) statsRaw[1];  // 0~9사이의 값

        return MemberResDTO.MemberMissionStats.builder()
                .completedCount(remainder)
                .missionCompletionRate((long) remainder.intValue())  // 예: 7
                .build();
    }

    // 회원 가입 응답 DTO 변환
    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member m
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(m.getId())
                .createAt(m.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birthDate(dto.birthDate())
                .location(dto.location())
                .gender(dto.gender())
                .build();
    }
}
