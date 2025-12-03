package com.hyunwjd.umc9th.domain.review.repository;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {



    // [조회] 가게별 리뷰 페이지네이션 (기본)
    Page<Review> findByStoreId(Long storeId, Pageable pageable);

    // ✏️✏️✏️[조회] 회원별 최신 20개 (JPQL @Query 방식으로 수정해봄)
    // List<Review> findTop20ByMember_IdOrderByCreatedAtDesc(Long memberId);

    //⚠️워크북 5주차 트러블슈팅 4번에 정리해둠⚠️
    @Query("select r from Review r where r.member.id = :memberId")
    Page<Review> findRecentReviewsByMember(@Param("memberId") Long memberId, Pageable pageable);
        // 호출: PageRequest.of(0, 20, Sort.by(DESC, "createdAt"))


    // [검증] 회원이 특정 가게에 남긴 리뷰 존재 여부
    boolean existsByMemberIdAndStoreId(Long memberId, Long storeId);

    // [성능 옵션] 사진/회원 같이 로딩하고 싶을 때(N+1 완화)
    @EntityGraph(attributePaths = {"photos", "member"})
    Page<Review> findByStore_IdAndStarGradeBetween(Long storeId, Integer min, Integer max, Pageable pageable);

    Page<Review> findAllByStore(Store store, Pageable pageable);

    Page<Review> findAllByMember(Member member);

    List<Review> memberId(Long memberId);
}
