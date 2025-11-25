package com.hyunwjd.umc9th.domain.member.service.query;

import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import org.springframework.transaction.annotation.Transactional;


public interface MemberQueryService {
    //달성도 조회
    @Transactional(readOnly = true)
    // 통계 DTO를 바로 반환하게 설계하는 걸 추천
    MemberResDTO.MemberMissionStats getMemberMissionStats(Long memberId);

}
