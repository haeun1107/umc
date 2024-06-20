package umc.spring.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class SuccessStatusDTO {
    private final String message;
    private final String code;
    private final boolean isSuccess;
    private final HttpStatus httpStatus;
}
