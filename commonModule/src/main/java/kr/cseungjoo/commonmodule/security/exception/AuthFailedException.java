package kr.cseungjoo.commonmodule.security.exception;


import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;
    
public class AuthFailedException extends BasicException {
    public AuthFailedException() {
        super(ErrorCode.AUTH_FAILED);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.AUTH_FAILED;
    }
}
