package kr.cseungjoo.authserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class RefreshTokenNotValidException extends BasicException {
    public RefreshTokenNotValidException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_VALID);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.REFRESH_TOKEN_NOT_VALID;
    }
}
