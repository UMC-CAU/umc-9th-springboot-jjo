package com.hyunwjd.umc9th.domain.member.service.command;

import com.hyunwjd.umc9th.domain.member.dto.req.MemberReqDTO;
import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MemberCommandService {
    //회원가입
    @Transactional
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
