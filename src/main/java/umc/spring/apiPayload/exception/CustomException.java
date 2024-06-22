package umc.spring.apiPayload.exception;

import umc.spring.code.BaseCode;

public class CustomException extends RuntimeException {
    private final BaseCode code;

    public CustomException(BaseCode code) {
        super(code.getReason().getMessage());
        this.code = code;
    }

    public BaseCode getCode() {
        return code;
    }
}
