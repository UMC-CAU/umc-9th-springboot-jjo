package com.hyunwjd.umc9th.domain.member.entity.mapping;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.member.entity.Term;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "member_term",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_member_term_member_term",
                        columnNames = {"member_id", "term_id"}
                )
        })

public class MemberTerm
{
    //MemberTermId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_term_id")
    private Long id;

    //FK: MemberId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //FK: TermId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

    // 동의 여부
    @Column(name = "is_agreed", nullable = false)
    private boolean isAgreed;

    // 동의 일시
    @Column(name = "agreed_at", nullable = false)
    private LocalDateTime agreedAt;
}
