package com.hyunwjd.umc9th.domain.member.dto.req;

import com.hyunwjd.umc9th.domain.location.Location;
import com.hyunwjd.umc9th.domain.location.enums.City;
import com.hyunwjd.umc9th.domain.member.enums.Gender;
import com.hyunwjd.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    // 회원가입 DTO
    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birthDate,
            @NotNull
            Location location,
            @ExistFoods
            List<Long> preferCategory

    ){}
}
