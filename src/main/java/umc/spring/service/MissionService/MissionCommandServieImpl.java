package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.code.status.ErrorStatus;
import umc.spring.entity.Mission;
import umc.spring.entity.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServieImpl implements MissionCommandService {

    public final MissionRepository missionRepository;
    public final StoreRepository storeRepository;

    @Transactional
    public MissionResponseDTO addMission(MissionRequestDTO missionRequestDTO) {
        Store store = storeRepository.findById(missionRequestDTO.getStoreId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = Mission.builder()
                .deadline(missionRequestDTO.getDeadline())
                .reward(missionRequestDTO.getReward())
                .id(store.getId())
                .missionSpec(missionRequestDTO.getMissionSpec())
                .build();

        Mission savedMission = missionRepository.save(mission);

        return MissionResponseDTO.builder()
                .id(savedMission.getId())
                .deadline(savedMission.getDeadline())
                .reward(savedMission.getReward())
                .storeId(store.getId())
                .missionSpec(savedMission.getMissionSpec())
                .build();
    }
}
