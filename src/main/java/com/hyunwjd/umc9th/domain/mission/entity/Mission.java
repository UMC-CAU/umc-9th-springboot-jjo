package com.hyunwjd.umc9th.domain.mission.entity;

import com.hyunwjd.umc9th.domain.mission.mapping.MemberMission;
import com.hyunwjd.umc9th.domain.store.entity.Store;
import com.hyunwjd.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
//@Setter <--무분별한 사용 지양
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mission",
        indexes = {
                @Index(name = "idx_mission_deadline", columnList = "deadline"),
                @Index(name = "idx_mission_store", columnList = "store_id")
        })
public class Mission extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Setter
    @Column(name = "complete_condition", length = 255, nullable = false)
    private String completeCondition;

    @Setter
    @Column(name = "point", nullable = false)
    private Integer point;


    // FK: store_id (Store 테이블과 N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // FK: member_mission (MemberMission 테이블과 1:N)
    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();

}
