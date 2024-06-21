package umc.spring.web.dto;

import lombok.Data;

@Data
public class MemberMissionRequestDTO {
    private Long memberId;
    private Long missionId;
}
