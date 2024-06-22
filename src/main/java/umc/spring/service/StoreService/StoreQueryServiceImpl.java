package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.entity.Review;
import umc.spring.entity.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewPreviewDTO;
import umc.spring.web.dto.ReviewPreviewListDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public ReviewPreviewListDTO getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();
        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        ReviewPreviewListDTO dto = ReviewPreviewListDTO.builder()
                .reviewList(StorePage.getContent().stream()
                        .map(review -> ReviewPreviewDTO.builder()
                                .ownerNickname(review.getMember().getName()) // Member 엔티티의 name 필드 접근
                                .score(review.getScore())
                                .body(review.getBody())
                                .createdAt(review.getCreatedAt().toLocalDate())
                                .build())
                        .collect(Collectors.toList()))
                .listSize(StorePage.getSize())
                .totalPage(StorePage.getTotalPages())
                .totalElements(StorePage.getTotalElements())
                .isFirst(StorePage.isFirst())
                .isLast(StorePage.isLast())
                .build();
        return dto;
    }
}