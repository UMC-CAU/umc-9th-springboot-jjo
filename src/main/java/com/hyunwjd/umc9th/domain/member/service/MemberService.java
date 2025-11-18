package com.hyunwjd.umc9th.domain.member.service;

import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.member.repository.MemberRepository;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 정보 조회
    public MemberResDTO.MemberInfo getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        return MemberResDTO.MemberInfo.builder()
                .memberId(member.getId())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .point(member.getPoint())
                .build();
    }

    // 회원의 미션 목록 조회 서비스 메서드
    public List<MemberResDTO.MemberMissionList> getMemberMissions(Long memberId) {
        Member member = memberRepository.findWithMissionsById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        List<MemberMission> memberMissions = member.getMemberMissions(); // 연관관계 컬렉션

        return memberMissions.stream()
                .map(mm -> MemberResDTO.MemberMissionList.builder()
                        .missionId(mm.getMission().getId())
                        .missionName(mm.getMission().getName())
                        .isComplete(mm.isComplete())
                        .completedAt(mm.getCompletedAt())
                        .point(mm.getMission().getPoint())
                        .createdAt(mm.getCreatedAt().toLocalDate())
                        .updateAt(mm.getUpdatedAt().toLocalDate())
                        .build()
                )
                .toList();
    }

    // 회원의 미션 완료 달성도 조회 서비스 메서드
    public MemberResDTO.MemberMissionStats getMemberMissionStats(Long memberId) {
        Object[] statsRaw = (Object[]) memberRepository.findCompletionStatsByMember(memberId);

        Long completedCount = (Long) statsRaw[0];
        Long remainder = (Long) statsRaw[1];

        return MemberResDTO.MemberMissionStats.builder()
                .completedCount(completedCount)
                .remainderToNextReward(remainder)
                .build();
    }


}
