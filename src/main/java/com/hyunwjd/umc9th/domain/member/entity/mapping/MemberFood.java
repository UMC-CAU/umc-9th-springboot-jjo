package com.hyunwjd.umc9th.domain.member.entity.mapping;


import com.hyunwjd.umc9th.domain.member.entity.Food;
import com.hyunwjd.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//기본 생성자(new MemberFood())를 외부에서는 못 쓰게 하고, JPA나 내부 코드에서만 쓰도록 제한
//JPA 기본생성자용으로 가장 많이 쓰이는데, 같은 패키지 or 상속받은 클래스에서만 호출 가능
//왜?
//외부에서 막 호출될 경우 데이터 무결성이 꺠지거나, 잘못된 상태로 객체가 생성될 수 있음
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//모든 필드를 매개변수로 받는 생성자를 외부에서 직접 호출하지 못하게 함
//빌더나 정적 팩토리 패턴에서 사용하는데, 클래스 내부에서만 호출 가능

//이 코드는 “JPA가 쓸 기본 생성자만 허용하고, 개발자는 빌더로만 객체를 생성하게끔 제한”하는 안전한 패턴
@Getter
@Table(name = "member_food")

public class MemberFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "food_id")
    private Food food;
}
