package com.hyunwjd.umc9th.domain.review.service.query;

import com.hyunwjd.umc9th.domain.location.enums.City;
import com.hyunwjd.umc9th.domain.review.converter.ReviewConverter;
import com.hyunwjd.umc9th.domain.review.dto.ReviewResDTO;
import com.hyunwjd.umc9th.domain.review.entity.QReview;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.repository.ReviewRepository;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import com.hyunwjd.umc9th.domain.store.exception.StoreException;
import com.hyunwjd.umc9th.domain.store.exception.code.StoreErrorCode;
import com.hyunwjd.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hyunwjd.umc9th.domain.location.QLocation.location;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

        // 가게 리뷰 조회 API
        @Override
        public ReviewResDTO.ReviewPreViewListDTO findReview(
                String storeName,
                Integer page
        ){
        // 가게를 가져온다
        Store store = storeRepository.findByName(storeName)
                // 예외 처리
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        // 가게의 리뷰 목록을 가져온다
        PageRequest pageRequest = PageRequest.of(page, 5); // 한 페이지에 5개 리뷰
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        // 결과를 응답 DTO로 변환
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

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
    public ReviewResDTO.ReviewPreViewListDTO findMyReviews(
            Long memberId,
            String query,
            String type,
            int page
    ) {
        PageRequest pageRequest = PageRequest.of(page, 5);

        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder()
                .and(review.member.id.eq(memberId));

        if ("store".equalsIgnoreCase(type) && query != null) {
            builder.and(review.store.name.containsIgnoreCase(query));
        }
        else if ("starBucket".equalsIgnoreCase(type) && query != null) {
            int bucket = Integer.parseInt(query);
            double from = bucket;
            double to   = (bucket == 5) ? 5.000001 : bucket + 1;

            if (bucket == 5) {
                builder.and(review.starGrade.eq((int) 5.0));
            } else {
                builder.and(review.starGrade.goe(from)
                        .and(review.starGrade.lt(to)));
            }
        }

        Page<Review> result = reviewRepository.findRecentReviewsByMember(memberId, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

}
