package com.hyunwjd.umc9th.domain.review.service.query;

import com.hyunwjd.umc9th.domain.location.enums.City;
import com.hyunwjd.umc9th.domain.review.entity.QReview;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hyunwjd.umc9th.domain.location.QLocation.location;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> searchReview(
            String query,
            String type
    ) {
        //Q클래스 정의
        QReview review = QReview.review;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        // type: location, star, both
        // 위치별 (Enum eq 매칭)
        if ("location".equalsIgnoreCase(type) && query != null) {
            City cityEnum = City.valueOf(query.toUpperCase()); // 예: "SEOUL"
            builder.and(location.city.eq(cityEnum));
        }
        // 별점별
        if (type.equals("star")) {
            builder.and(review.starGrade.goe(Float.parseFloat(query)));
        }
        // 위치 + 별점
        if (type.equals("both")) {
            String firstQuery = query.split("&")[0];   // "SEOUL" 같은 값이라 가정
            String secondQuery = query.split("&")[1];

            // 문자열 → Enum 으로 변환
            City cityEnum = City.valueOf(firstQuery.toUpperCase());

            builder.and(location.city.eq(cityEnum));
            builder.and(review.starGrade.goe(Float.parseFloat(secondQuery)));
        }
        //Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        //결과 반환
        return reviewList;
    }


    // 내가 작성한 리뷰 조회 API
    @Override
    public List<Review> findMyReviews(
            Long memberId,
            String query,
            String type
    ) {
        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder()
                .and(review.member.id.eq(memberId)); // TODO: 임시 memberId 고정, 추후 인증 정보로 대체

        // BooleanBuilder 사용

        // 동적 쿼리: 필터 조건
        // type: store, starGrade
        if ("store".equalsIgnoreCase(type) && query != null) {
            builder.and(review.store.name.containsIgnoreCase(query));
        } else if ("starBucket".equalsIgnoreCase(type) && query != null) {
            int bucket = Integer.parseInt(query);   // 5,4,3,...
            double from = bucket;                   // 4 → 4.0
            double to   = bucket == 5 ? 5.000001 : bucket + 1.0; // 5점은 eq 처리해도 OK
            if (bucket == 5) {
                builder.and(review.starGrade.eq((int) 5.0));
            } else {
                builder.and(review.starGrade.goe(from).and(review.starGrade.lt(to)));
            }
        }


        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.findReviewsByMemberId(builder);

        // 결과 반환
        return reviewList;
    }
}
