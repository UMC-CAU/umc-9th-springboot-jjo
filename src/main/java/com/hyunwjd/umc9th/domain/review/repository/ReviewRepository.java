package com.hyunwjd.umc9th.domain.review.repository;

import com.hyunwjd.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1) 가게별 리뷰 페이징 (메서드 이름 기반) 연관경로는 _로 연결
    Page<Review> findByStore_Id(Long storeId, Pageable pageable);

    // 2) 회원별 최신 리뷰 20개 (메서드 이름 기반)
    List<Review> findTop20ByMemberIdOrderByCreatedAtDesc(Long memberId);

    // 3) 특정 회원이 특정 가게에 리뷰를 남겼는지 (메서드 이름 기반)
    boolean existsByMemberIdAndStoreId(Long memberId, Long storeId);

    // 4) 사용자가 남긴 별점 (메서드 이름 기반)
    List<Integer> findStarGradeByMember_Id(Long memberId);


    // 5) 가게의 리뷰 수 (메서드 이름 기반)
    long countByStoreId(Long storeId);
}

