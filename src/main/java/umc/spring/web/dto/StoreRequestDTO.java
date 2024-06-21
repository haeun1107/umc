package umc.spring.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreRequestDTO {
    private Float score;
    private Long regionId;
    private String address;
    private String name;
}
