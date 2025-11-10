package com.hyunwjd.umc9th.domain.member.entity;

import com.hyunwjd.umc9th.domain.member.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private TermType name;   // 약관 종류 (AGE, SERVICE, PRIVACY, LOCATION, MARKETING)

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;  // 약관 내용 (텍스트 전체 내용)

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;  // 필수 여부 (true = 필수 약관, false = 선택 약관)

    @Column(name = "version", nullable = false)
    private String version;  // 약관 버전 (예: v1.0, v2.0)

    // 도메인 메서드 예시
    public void updateContent(String newContent, String newVersion) {
        this.content = newContent;
        this.version = newVersion;
    }
}
