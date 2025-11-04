package com.hyunwjd.umc9th.domain.review.entity;

import com.hyunwjd.umc9th.domain.store.entity.Store;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Review extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "review_text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "star_grade", nullable = false)
    private Integer starGrade;


    // FK: store_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


    // FK: member_id → Member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    //FK: ReviewPhoto 관계 (1:N)
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> photos = new ArrayList<>();


    //FK: ReviewComment 관계 (1:1 -> 워크북대로)
    @OneToOne(mappedBy = "review",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "review_comment_id")
    private ReviewComment reviewComment;
}
