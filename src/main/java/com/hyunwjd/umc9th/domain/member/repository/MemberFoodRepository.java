package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.member.entity.mapping.MemberFood;

import java.util.List;

public interface MemberFoodRepository {

    void saveAll(List<MemberFood> memberFood);

    List<MemberFood> findbyMember(Member member);

}
