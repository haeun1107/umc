package umc.spring.service.MissionService;

import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    public MissionResponseDTO addMission(MissionRequestDTO missionRequestDTO);
    public MemberMissionResponseDTO challengeMission(MemberMissionRequestDTO memberMissionRequestDTO);
    MemberMissionResponseDTO completeMission(Long memberMissionId);
}
