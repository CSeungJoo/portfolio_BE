package kr.cseungjoo.profileservice.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class ProfileNotFoundException extends BasicException {
    private ErrorCode errorCode;

    public ProfileNotFoundException() {
        super(ErrorCode.PROFILE_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
