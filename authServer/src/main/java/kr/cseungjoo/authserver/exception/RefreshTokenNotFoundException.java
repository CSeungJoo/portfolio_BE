package kr.cseungjoo.authserver.exception;


import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class RefreshTokenNotFoundException extends BasicException {
    public RefreshTokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
