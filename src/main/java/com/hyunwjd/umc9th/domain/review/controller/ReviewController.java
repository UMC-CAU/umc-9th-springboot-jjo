package com.hyunwjd.umc9th.domain.review.controller;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.converter.ReviewConverter;
import com.hyunwjd.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.dto.res.ReviewResDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.service.query.ReviewQueryService;
import com.hyunwjd.umc9th.domain.review.service.query.ReviewQueryServiceImpl;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;
import com.hyunwjd.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.*;
import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class ReviewController {

    private final ReviewQueryServiceImpl reviewQueryServiceImpl;
    private final ReviewQueryService reviewCommandService;


    // 리뷰 등록 API
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateResult> createReview(
            @PathVariable Long storeId,
            @RequestParam Member memberId,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        Review review = reviewCommandService.createReview(storeId, memberId, request);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toCreateResult(review)
        );
    }

    // 리뷰 검색 API
    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        // Service에게 요청
        List<Review> result = reviewQueryServiceImpl.searchReview(query, type);
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
        List<Review> result = reviewQueryServiceImpl.findMyReviews(memberId, query, type);
        return result;
    }
}