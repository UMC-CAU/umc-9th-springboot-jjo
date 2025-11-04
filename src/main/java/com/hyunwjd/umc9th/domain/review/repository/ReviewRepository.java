package com.hyunwjd.umc9th.domain.review.repository;

import com.hyunwjd.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // [작성] 리뷰 저장 (INSERT는 save 사용)
    // Review save(Review review);  // JpaRepository가 이미 제공

    // [조회] 가게별 리뷰 페이지네이션 (기본)
    Page<Review> findByStore_Id(Long storeId, Pageable pageable);

    // [조회] 가게별 + 최신순 정렬(옵션)
    Page<Review> findByStore_IdOrderByCreatedAtDesc(Long storeId, Pageable pageable);

    // [조회] 회원별 최신 20개
    List<Review> findTop20ByMember_IdOrderByCreatedAtDesc(Long memberId);

    // [조회] 회원이 특정 가게에 남긴 리뷰 존재 여부
    boolean existsByMember_IdAndStore_Id(Long memberId, Long storeId);

    // [조회] 특정 가게의 리뷰 개수
    long countByStore_Id(Long storeId);

    // [조회] 회원별 별점 필터 (예: 별점 4~5만)
    Page<Review> findByMember_IdAndStarGradeBetween(Long memberId, Integer min, Integer max, Pageable pageable);

    // [조회] 기간 필터 (예: 최근 한 달)
    Page<Review> findByStore_IdAndCreatedAtBetween(Long storeId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    // [조회] 가게 + 회원 복합 조건 (필요 시)
    Page<Review> findByStore_IdAndMember_Id(Long storeId, Long memberId, Pageable pageable);

    // [성능 옵션] 사진/회원 같이 로딩하고 싶을 때(N+1 완화)
    @EntityGraph(attributePaths = {"photos", "member"})
    Page<Review> findByStore_IdAndStarGradeBetween(Long storeId, Integer min, Integer max, Pageable pageable);

    // [정렬 옵션 예시] 별점 높은 순
    Page<Review> findByStore_IdOrderByStarGradeDesc(Long storeId, Pageable pageable);

    // [정렬 옵션 예시] 별점/최신 복합 (메서드명만으론 복합정렬 제약 → Pageable.sort로 해결 권장)
    // 권장 사용: PageRequest.of(page, size, Sort.by(DESC, "starGrade").and(Sort.by(DESC, "createdAt")))
}
