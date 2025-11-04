package com.hyunwjd.umc9th.domain.mission.mapping;

import com.hyunwjd.umc9th.domain.member.entity.Member;
import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "member_mission",
        indexes = {
                @Index(name = "ix_member_mission_member_complete", columnList = "member_id, is_complete, member_mission_id"),
                @Index(name = "ix_member_mission_mission", columnList = "mission_id")
        }
        , uniqueConstraints = @UniqueConstraint(name = "uk_member_mission_member_mission", columnNames = {"member_id", "mission_id"})
)
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(name = "is_complete", nullable = false)
    private boolean isComplete;
}
