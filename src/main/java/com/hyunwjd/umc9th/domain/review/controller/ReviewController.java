package com.hyunwjd.umc9th.domain.review.controller;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.converter.ReviewConverter;
import com.hyunwjd.umc9th.domain.review.dto.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.dto.ReviewResDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.hyunwjd.umc9th.domain.review.service.command.ReviewCommandService;
import com.hyunwjd.umc9th.domain.review.service.query.ReviewQueryService;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;
import com.hyunwjd.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.*;
import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;

import java.util.List;

import static com.hyunwjd.umc9th.domain.store.entity.QStore.store;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewCommandService reviewCommandService;
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


    //가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ) {

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    // 내가 작성한 리뷰 조회 API
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "0") int page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(
                code,
                reviewQueryService.findMyReviews(memberId, query, type, page)
        );
    }

}


