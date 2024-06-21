package umc.spring.web.dto;

import lombok.Builder;
import lombok.Data;
import umc.spring.entity.enums.MissionStatus;

@Data
@Builder
public class MemberMissionResponseDTO {
    private Long id;
    private Long memberId;
    private Long missionId;
    private MissionStatus status;
}
