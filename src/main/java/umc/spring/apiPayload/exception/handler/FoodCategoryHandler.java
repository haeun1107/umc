package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.code.BaseErrorCode;
import umc.spring.code.status.ErrorStatus;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}