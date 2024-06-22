package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.code.status.ErrorStatus;
import umc.spring.entity.Member;
import umc.spring.entity.Mission;
import umc.spring.entity.Store;
import umc.spring.entity.enums.MissionStatus;
import umc.spring.entity.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionPreviewListDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionPreviewListDTO getMissionsByStore(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.STORE_NOT_FOUND));

        Page<Mission> missions = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return MissionPreviewListDTO.builder()
                .missions(missions.getContent().stream()
                        .map(mission -> MissionResponseDTO.builder()
                                .id(mission.getId())
                                .deadline(mission.getDeadline())
                                .reward(mission.getReward())
                                .storeId(store.getId())
                                .missionSpec(mission.getMissionSpec())
                                .build())
                        .collect(Collectors.toList()))
                .listSize(missions.getSize())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }

    @Override
    public MissionPreviewListDTO getUserChallengingMissions(Long userId, Integer page) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));

        Page<MemberMission> memberMissions = memberMissionRepository.findByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page, 10));

        return MissionPreviewListDTO.builder()
                .missions(memberMissions.getContent().stream()
                        .map(memberMission -> {
                            Mission mission = memberMission.getMission();
                            return MissionResponseDTO.builder()
                                    .id(mission.getId())
                                    .deadline(mission.getDeadline())
                                    .reward(mission.getReward())
                                    .storeId(mission.getStore().getId())
                                    .missionSpec(mission.getMissionSpec())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .listSize(memberMissions.getSize())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }
}
