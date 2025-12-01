package com.hyunwjd.umc9th.domain.review.service.command;

import com.hyunwjd.umc9th.domain.location.enums.City;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.review.converter.ReviewConverter;
import com.hyunwjd.umc9th.domain.review.dto.ReviewReqDTO;
import com.hyunwjd.umc9th.domain.review.entity.QReview;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.review.repository.ReviewRepository;
import com.hyunwjd.umc9th.domain.review.service.command.ReviewCommandService;
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
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    //리뷰 작성 API
    @Override
    public Review createReview(Long storeId, Member member, ReviewReqDTO.CreateReview request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, store, member);

        return reviewRepository.save(review);
    }


    }



