package kr.cseungjoo.profileserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class ProfileNotFoundException extends BasicException {
    public ProfileNotFoundException() {
        super(ErrorCode.PROFILE_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.PROFILE_NOT_FOUND;
    }
}
