package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {

    void saveAll(List<MemberFood> memberFood);

    List<MemberFood> findbByMember(Member member);

}
