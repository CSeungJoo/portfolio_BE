package kr.cseungjoo.profileserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class ProfileExistsException extends BasicException {
    public ProfileExistsException() {
        super(ErrorCode.PROFILE_EXISTS);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
