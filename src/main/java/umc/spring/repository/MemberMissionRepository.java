package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
