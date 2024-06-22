package umc.spring.entity.mapping;
import jakarta.persistence.*;
import lombok.*;
import umc.spring.entity.Member;
import umc.spring.entity.Mission;
import umc.spring.entity.common.BaseEntity;
import umc.spring.entity.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    public void updateStatus(MissionStatus status) {
        this.status = status;
    }
}