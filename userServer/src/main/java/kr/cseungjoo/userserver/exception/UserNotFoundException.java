package kr.cseungjoo.userserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.USER_NOT_FOUND;
    }
}
