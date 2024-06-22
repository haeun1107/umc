package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.web.dto.MissionPreviewListDTO;
import umc.spring.web.dto.MissionResponseDTO;

public interface MissionQueryService {
    MissionPreviewListDTO getMissionsByStore(Long storeId, Integer page);
}
