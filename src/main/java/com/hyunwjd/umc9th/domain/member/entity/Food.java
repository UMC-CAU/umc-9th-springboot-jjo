package com.hyunwjd.umc9th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import com.hyunwjd.umc9th.domain.member.enums.FoodName;

public class Food {
    @Enumerated(EnumType.STRING)
    private FoodName name;
}
