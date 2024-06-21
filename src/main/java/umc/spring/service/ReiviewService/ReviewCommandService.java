package umc.spring.service.ReiviewService;

import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO);
}
