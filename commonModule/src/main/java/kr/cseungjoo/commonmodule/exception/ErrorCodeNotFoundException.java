package kr.cseungjoo.commonmodule.exception;

public class ErrorCodeNotFoundException extends BasicException {

    public ErrorCodeNotFoundException() {
        super(ErrorCode.ERROR_CODE_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.ERROR_CODE_NOT_FOUND;
    }
}