package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.Member;
import umc.spring.entity.enums.MissionStatus;
import umc.spring.entity.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);}
