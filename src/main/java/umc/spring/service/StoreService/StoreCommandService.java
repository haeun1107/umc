package umc.spring.service.StoreService;

import umc.spring.entity.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

public interface StoreCommandService {
    public StoreResponseDTO addStore(StoreRequestDTO requestDTO);
}
