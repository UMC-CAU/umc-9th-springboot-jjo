package com.hyunwjd.umc9th.domain.member.entity;

import com.hyunwjd.umc9th.domain.member.entity.mapping.MemberFood;
import com.hyunwjd.umc9th.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodName name;

    // ── MemberFood 관계 (1:N)
    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
