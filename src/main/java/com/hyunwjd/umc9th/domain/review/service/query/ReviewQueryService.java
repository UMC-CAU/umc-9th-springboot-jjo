package com.hyunwjd.umc9th.domain.review.service.query;

import com.hyunwjd.umc9th.domain.review.dto.ReviewResDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {

    // 가게 리뷰 조회 API
    ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );

    // 리뷰 검색 API
    List<Review> searchReview(String query, String type);

    // 내가 작성한 리뷰 조회 API
    ReviewResDTO.ReviewPreViewListDTO findMyReviews(
            Long memberId,
            String query,
            String type,
            int page
    );
}
