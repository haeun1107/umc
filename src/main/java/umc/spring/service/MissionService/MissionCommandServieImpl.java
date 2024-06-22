package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
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
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServieImpl implements MissionCommandService {

    public final MissionRepository missionRepository;
    public final StoreRepository storeRepository;
    public final MemberRepository memberRepository;
    public final MemberMissionRepository memberMissionRepository;

    @Transactional
    public MissionResponseDTO addMission(MissionRequestDTO missionRequestDTO) {
        Store store = storeRepository.findById(missionRequestDTO.getStoreId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = Mission.builder()
                .deadline(missionRequestDTO.getDeadline())
                .reward(missionRequestDTO.getReward())
                .store(store)
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

    @Transactional
    public MemberMissionResponseDTO challengeMission(MemberMissionRequestDTO memberMissionRequestDTO) {
        Member member = memberRepository.findById(memberMissionRequestDTO.getMemberId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(memberMissionRequestDTO.getMissionId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        return MemberMissionResponseDTO.builder()
                .id(savedMemberMission.getId())
                .memberId(savedMemberMission.getMember().getId())
                .missionId(savedMemberMission.getMission().getId())
                .status(savedMemberMission.getStatus())
                .build();
    }

    @Transactional
    public MemberMissionResponseDTO completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        memberMission.updateStatus(MissionStatus.COMPLETE);
        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        return MemberMissionResponseDTO.builder()
                .id(savedMemberMission.getId())
                .memberId(savedMemberMission.getMember().getId())
                .missionId(savedMemberMission.getMission().getId())
                .status(savedMemberMission.getStatus())
                .build();
    }
}
