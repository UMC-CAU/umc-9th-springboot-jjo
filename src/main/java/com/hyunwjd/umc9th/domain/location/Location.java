package com.hyunwjd.umc9th.domain.location;


import com.hyunwjd.umc9th.domain.location.enums.City;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "City_name", nullable = false)
    private City city;  // 시/도 단위

    @Column(name = "detail_address", length = 100)
    private String detail;          // 상세 주소

    @Column(name = "postal_code", length = 10)
    private String postalCode;      // 우편번호...필요할까?
}