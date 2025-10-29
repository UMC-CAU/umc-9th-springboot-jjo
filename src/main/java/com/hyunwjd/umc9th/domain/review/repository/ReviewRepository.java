package com.hyunwjd.umc9th.domain.review.repository;

import com.hyunwjd.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1) 가게별 리뷰 페이징 (메서드 이름 기반)
    Page<Review> findByStoreId(Long storeId, Pageable pageable);

    // 2) 회원별 최신 리뷰 10개 (메서드 이름 기반)
    List<Review> findTop10ByMemberIdOrderByCreatedAtDesc(Long memberId);

    // 3) 특정 회원이 특정 가게에 리뷰를 남겼는지 (메서드 이름 기반)
    boolean existsByMemberIdAndStoreId(Long memberId, Long storeId);

    // 4) 가게 평균 별점 (JPQL @Query)
    @Query("select avg(r.starGrade) from Review r where r.store.id = :storeId")
    Double findAvgStarByStoreId(@Param("storeId") Long storeId);

    // 5) 가게의 리뷰 수 (메서드 이름 기반)
    long countByStoreId(Long storeId);
}

