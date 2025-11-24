package com.hyunwjd.umc9th.domain.review.service.query;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.converter.ReviewConverter;
import com.hyunwjd.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.entity.QReview;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.repository.ReviewRepository;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import com.hyunwjd.umc9th.domain.store.exception.code.StoreErrorCode;
import com.hyunwjd.umc9th.domain.store.repository.StoreRepository;
import com.hyunwjd.umc9th.global.apiPayload.exception.GeneralException;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hyunwjd.umc9th.domain.location.QLocation.location;

@Transactional
@Service
@RequiredArgsConstructor // 생성자 자동 생성이라서 필요함 ㅠㅠ
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    //리뷰 작성
    @Override
    public Review createReview(Long storeId, Member member, ReviewReqDTO.CreateReview request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, store, (java.lang.reflect.Member) member);

        return reviewRepository.save(review);
    }

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
        // 위치별
        if (type.equals("location")) {
            builder.and(location.city.contains(query));
        }
        // 별점별
        if (type.equals("star")) {
            builder.and(review.starGrade.goe(Float.parseFloat(query)));
        }
        // 위치 + 별점
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(location.city.contains(firstQuery));
            builder.and(review.starGrade.goe(Float.parseFloat(secondQuery)));
        }
        //Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        //결과 반환
        return reviewList;
    }

    // 내가 작성한 리뷰 조회 API
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



