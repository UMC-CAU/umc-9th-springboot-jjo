package com.hyunwjd.umc9th.domain.member.repository;

import com.hyunwjd.umc9th.domain.member.entity.Food;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FoodRepository {
    Optional<Food> findById(Long id);

    boolean existsById(Long value);
}
