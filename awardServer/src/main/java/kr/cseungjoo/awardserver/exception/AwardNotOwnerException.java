package kr.cseungjoo.awardserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class AwardNotOwnerException extends BasicException {
    public AwardNotOwnerException() {
        super(ErrorCode.AWARD_NOT_OWNER);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.AWARD_NOT_OWNER;
    }
}