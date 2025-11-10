package com.hyunwjd.umc9th.domain.review.repository;

import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    //검색 API
    List<Review> searchReview(
            Predicate predicate
    );

    //내가 작성한 리뷰 조회 API
    List <Review> findReviewsByMemberId(
            Predicate predicate
    );
}

