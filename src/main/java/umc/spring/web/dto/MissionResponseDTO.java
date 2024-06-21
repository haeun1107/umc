package umc.spring.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MissionResponseDTO {
    private Long id;
    private LocalDate deadline;
    private int reward;
    private Long storeId;
    private String missionSpec;
}
