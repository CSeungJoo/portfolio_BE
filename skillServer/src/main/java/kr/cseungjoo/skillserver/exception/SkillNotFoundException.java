package kr.cseungjoo.skillserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class SkillNotFoundException extends BasicException {
    public SkillNotFoundException() {
        super(ErrorCode.SKILL_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.SKILL_NOT_FOUND;
    }
}
