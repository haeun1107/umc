package umc.spring.web.dto;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    Long memberId;
    Long storeId;
    String title;
    Float score;
}
