package com.hyunwjd.umc9th.domain.member.entity;

import com.hyunwjd.umc9th.domain.location.Location;
import com.hyunwjd.umc9th.domain.member.entity.mapping.MemberFood;
import com.hyunwjd.umc9th.domain.member.enums.SocialType;
import com.hyunwjd.umc9th.domain.member.enums.MemberStatus;
import com.hyunwjd.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.hyunwjd.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")

public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "nickname", length = 10, nullable = false)
    private String nickname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "social_uid", length = 64)
    private String socialUid;

    @Column(name = "point")
    private Integer point;

    // 연관 관계
    // Member - Food (N:1)
    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoods = new ArrayList<>();

    // Member - MemberMission (1:N)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.hyunwjd.umc9th.domain.mission.mapping.MemberMission> memberMissions = new ArrayList<>();

    // Member - Location (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id" )
    private Location location;
}
