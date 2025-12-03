package com.hyunwjd.umc9th.domain.review.controller;

import com.hyunwjd.umc9th.domain.review.dto.ReviewResDTO;
import com.hyunwjd.umc9th.global.apiPayload.ApiResponse;

public interface ReviewControllerDocs {
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            String storeName,
            Integer page
    );

    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            Long memberId,
            String query,
            String type,
            int page
    );
}
