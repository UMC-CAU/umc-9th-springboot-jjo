package com.hyunwjd.umc9th.domain.review.converter;

import com.hyunwjd.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.entity.ReviewPhoto;
import com.hyunwjd.umc9th.domain.store.entity.Store;

import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    // Create DTO -> Review 엔티티
    public static Review toReview(ReviewReqDTO.CreateReview request, Store store, Member member) {
        Review review = Review.builder()
                .text(request.getText())
                .starGrade(request.getStarGrade())
                .store(store)
                .member(member)
                .build();

        // 사진까지 함께 저장하고 싶다면
        if (request.getPhotoUrls() != null && !request.getPhotoUrls().isEmpty()) {
            List<ReviewPhoto> photos = request.getPhotoUrls().stream()
                    .map(url -> ReviewPhoto.builder()
                            .url(url)
                            .review(review)
                            .build())
                    .collect(Collectors.toList());
            review.setPhotos(photos);
        }

        return review;
    }

    // Review 엔티티 -> CreateResult DTO
    public static ReviewResDTO.ReviewResult toCreateResult(Review review) {
        List<String> photoUrls = review.getPhotos().stream()
                .map(ReviewPhoto::getUrl)
                .collect(Collectors.toList());

        return ReviewResDTO.CreateResult.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .starGrade(review.getStarGrade())
                .text(review.getText())
                .photoUrls(photoUrls)
                .createdAt(review.getCreatedAt())
                .build();
    }
}
