package com.hyunwjd.umc9th.domain.store.entity;


import com.hyunwjd.umc9th.domain.location.Location;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store",
        indexes = {
                @Index(name = "idx_store_name", columnList = "store_name"),
                @Index(name = "idx_store_location", columnList = "location_id")
        })
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", length = 10, nullable = false)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    // manager_id: Member FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    private Member manager;

    //FK: location_id (Location 테이블과 N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}