package com.hyunwjd.umc9th.domain.member.service.command;

import com.hyunwjd.umc9th.domain.member.converter.MemberConverter;
import com.hyunwjd.umc9th.domain.member.dto.req.MemberReqDTO;
import com.hyunwjd.umc9th.domain.member.dto.res.MemberResDTO;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.member.entity.mapping.MemberFood;
import com.hyunwjd.umc9th.domain.member.exception.FoodException;
import com.hyunwjd.umc9th.domain.member.exception.code.FoodErrorCode;
import com.hyunwjd.umc9th.domain.member.repository.FoodRepository;
import com.hyunwjd.umc9th.domain.member.repository.MemberFoodRepository;
import com.hyunwjd.umc9th.domain.member.repository.MemberRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    //[아직 미구현]
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    //회원가입
    @Transactional
    @Override
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ) {
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFood = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            memberFoodRepository.saveAll(memberFood);
        }


    // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
}
    }


