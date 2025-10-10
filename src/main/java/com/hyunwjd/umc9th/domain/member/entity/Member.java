package com.hyunwjd.umc9th.domain.member.entity;

import com.hyunwjd.umc9th.domain.location.Location;
import com.hyunwjd.umc9th.domain.member.enums.SocialType;
import com.hyunwjd.umc9th.domain.member.enums.MemberStatus;
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

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id" )
    private Location location;

    @Column(name = "detail_address", length = 100, nullable = false)
    private String detailAddress;

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
}
