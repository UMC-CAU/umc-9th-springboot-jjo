package com.hyunwjd.umc9th.domain.mission.service.command;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.member.exception.code.MemberErrorCode;
import com.hyunwjd.umc9th.domain.member.repository.MemberRepository;
import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import com.hyunwjd.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.mission.repository.MemberMissionRepository;
import com.hyunwjd.umc9th.domain.mission.repository.MissionRepository;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public MemberMission challengeMission(Long missionId, Long memberId) {

        // 1) 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(MissionErrorCode.NOT_FOUND));

        // 2) 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));

        // 3) 이미 도전 중인지 확인
        if (memberMissionRepository.existsByMember_IdAndMission_Id(memberId, missionId)) {
            throw new GeneralException(MissionErrorCode.MISSION_ALREADY_IN_PROGRESS);
        }

        // 4) MemberMission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        return memberMissionRepository.save(memberMission);
    }
}
