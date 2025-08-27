package kr.cseungjoo.awardserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class AwardNotFoundException extends BasicException {
    public AwardNotFoundException() {
        super(ErrorCode.AWARD_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.AWARD_NOT_FOUND;
    }
}