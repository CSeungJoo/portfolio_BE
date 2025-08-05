package kr.cseungjoo.projectserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class ProjectNotFoundException extends BasicException {
    public ProjectNotFoundException() {
        super(ErrorCode.PROJECT_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}
