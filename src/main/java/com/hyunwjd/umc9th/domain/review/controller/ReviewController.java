package com.hyunwjd.umc9th.domain.review.controller;

import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 리뷰 검색 API
    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        // Service에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    // 내가 작성한 리뷰 조회 API
    @GetMapping("/reviews/my")
    public List<Review> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam String query,
            @RequestParam String type
    ) {
        // Service에게 요청
        List<Review> result = reviewQueryService.findMyReviews(memberId, query, type);
        return result;
    }
}