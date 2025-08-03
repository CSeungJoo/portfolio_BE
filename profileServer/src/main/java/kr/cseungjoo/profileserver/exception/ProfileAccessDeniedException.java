package kr.cseungjoo.profileserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class ProfileAccessDeniedException extends BasicException {
    public ProfileAccessDeniedException() {
        super(ErrorCode.PROFILE_ACCESS_DENIED);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.PROFILE_ACCESS_DENIED;
    }
}
