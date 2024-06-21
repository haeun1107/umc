package umc.spring.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MissionRequestDTO {
    private LocalDate deadline;
    private Integer reward;
    private Long storeId;
    private String missionSpec;
}
