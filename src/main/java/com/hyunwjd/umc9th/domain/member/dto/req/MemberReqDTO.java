package com.hyunwjd.umc9th.domain.member.dto.req;

import com.hyunwjd.umc9th.domain.location.Location;
import com.hyunwjd.umc9th.domain.location.enums.City;
import com.hyunwjd.umc9th.domain.member.enums.Gender;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    // 회원가입 DTO
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birthDate,
            Location location,
            List<Long> preferCategory
    ){}
}
