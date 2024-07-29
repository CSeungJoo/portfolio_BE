package kr.cseungjoo.jobcategory.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class JobCategoryNotFoundException extends BasicException {

    private ErrorCode errorCode;

    public JobCategoryNotFoundException() {
        super(ErrorCode.JOB_CATEGORY_NOT_FOUND);
    }

    @Override
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}