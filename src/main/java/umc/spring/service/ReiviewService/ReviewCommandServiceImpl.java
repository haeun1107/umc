package umc.spring.service.ReiviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.code.status.ErrorStatus;
import umc.spring.entity.Member;
import umc.spring.entity.Review;
import umc.spring.entity.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO) {
        Member member = memberRepository.findById(reviewRequestDTO.getMemberId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(reviewRequestDTO.getStoreId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .title(reviewRequestDTO.getTitle())
                .score(reviewRequestDTO.getScore())
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResponseDTO.builder()
                .id(savedReview.getId())
                .memberId(savedReview.getMember().getId())
                .storeId(savedReview.getStore().getId())
                .title(savedReview.getTitle())
                .score(savedReview.getScore())
                .build();
    }
}
