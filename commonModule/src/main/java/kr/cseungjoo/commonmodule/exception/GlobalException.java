package kr.cseungjoo.commonmodule.exception;

public class GlobalException extends BasicException {
    public GlobalException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
