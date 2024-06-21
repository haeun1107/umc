package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.code.status.ErrorStatus;
import umc.spring.entity.Region;
import umc.spring.entity.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public StoreResponseDTO addStore(StoreRequestDTO requestDTO) {
        Region region = regionRepository.findById(requestDTO.getRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        Store store = Store.builder()
                .score(requestDTO.getScore())
                .region(region)
                .address(requestDTO.getAddress())
                .name(requestDTO.getName())
                .build();

        Store savedStore = storeRepository.save(store);

        return StoreResponseDTO.builder()
                .id(savedStore.getId())
                .build();
    }
}
