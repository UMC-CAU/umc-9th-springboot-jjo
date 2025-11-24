package com.hyunwjd.umc9th.domain.review.service.query;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;

public interface ReviewQueryService {

    Review createReview(Long storeId, Member member, ReviewReqDTO.CreateReview request);
}
