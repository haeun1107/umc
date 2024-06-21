package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.code.BaseErrorCode;

public class RegionHandler extends GeneralException {

    public RegionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
