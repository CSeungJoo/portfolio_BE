package kr.cseungjoo.projectserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class ProjectNotFoundOrAccessDeniedException extends BasicException {
    public ProjectNotFoundOrAccessDeniedException() {
        super(ErrorCode.PROJECT_NOT_FOUND_OR_ACCESS_DENIED);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.PROJECT_NOT_FOUND_OR_ACCESS_DENIED;
    }
}
