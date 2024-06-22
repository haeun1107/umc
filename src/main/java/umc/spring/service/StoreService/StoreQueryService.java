package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.entity.Review;
import umc.spring.entity.Store;
import umc.spring.web.dto.ReviewPreviewListDTO;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    ReviewPreviewListDTO getReviewList(Long StoreId, Integer page);
    ReviewPreviewListDTO getUserReviews(Long userId, Integer page);

}