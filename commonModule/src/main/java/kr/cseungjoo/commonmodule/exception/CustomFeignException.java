package kr.cseungjoo.commonmodule.exception;

public class CustomFeignException extends BasicException{
    public CustomFeignException() {
        super(ErrorCode.OPEN_FEIGN_EXCEPTION);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.OPEN_FEIGN_EXCEPTION;
    }
}
