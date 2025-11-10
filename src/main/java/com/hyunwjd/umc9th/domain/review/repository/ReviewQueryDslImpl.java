//QueryDsl 구현체
package com.hyunwjd.umc9th.domain.review.repository;

import com.hyunwjd.umc9th.domain.location.QLocation;
import com.hyunwjd.umc9th.domain.review.entity.QReview;
import com.hyunwjd.umc9th.domain.review.entity.Review;
import com.hyunwjd.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final JPAQueryFactory query; //Bean 주입

    //검색 API
    @Override
    public List<Review> searchReview(
            Predicate predicate
    ) {
        //Q 클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QLocation location = QLocation.location;

        return query
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .fetch();
    }

    //내가 작성한 리뷰 조회 API
    @Override
    public List<Review> findReviewsByMemberId(
            Predicate predicate
    ) {
        //Q 클래스 선언
        QReview review = QReview.review;

        return query
                .selectFrom(review)
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.store.location).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
