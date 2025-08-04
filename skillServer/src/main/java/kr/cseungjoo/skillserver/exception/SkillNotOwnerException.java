package kr.cseungjoo.skillserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class SkillNotOwnerException extends BasicException {
    public SkillNotOwnerException() {
        super(ErrorCode.SKILL_NOT_OWNER);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.SKILL_NOT_OWNER;
    }
}
