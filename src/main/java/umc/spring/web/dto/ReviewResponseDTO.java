package umc.spring.web.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private Long memberId;
    private Long storeId;
    private String title;
    private Float score;
}
