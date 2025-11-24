package com.hyunwjd.umc9th.domain.member.service.query;

import com.hyunwjd.umc9th.domain.member.converter.MemberConverter;
import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.mission.repository.MemberMissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberResDTO.MemberMissionStats getMemberMissionStats(Long memberId) {

        // 1) 리포지토리에서 원시 통계 데이터(Object[]) 가져오기
        Object[] statsRaw = memberMissionRepository.getMissionStats(memberId);

        // 2) Converter를 이용해 DTO로 변환
        return MemberConverter.toMemberMissionStats(statsRaw);
    }
}
