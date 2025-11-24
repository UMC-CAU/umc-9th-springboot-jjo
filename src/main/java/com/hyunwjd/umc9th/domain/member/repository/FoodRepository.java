package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Food;

import java.util.Optional;

public interface FoodRepository {
    Optional<Food> findById(Long id);
}
