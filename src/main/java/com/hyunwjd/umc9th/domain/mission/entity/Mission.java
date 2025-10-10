package com.hyunwjd.umc9th.domain.mission.entity;

import com.hyunwjd.umc9th.domain.BaseTimeEntity;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mission",
        indexes = {
                @Index(name = "idx_mission_deadline", columnList = "deadline"),
                @Index(name = "idx_mission_store", columnList = "store_id")
        })
public class Mission extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "complete_condition", length = 255, nullable = false)
    private String completeCondition;

    @Column(name = "point", nullable = false)
    private Integer point;


    // FK: store_id (Store 테이블과 N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
