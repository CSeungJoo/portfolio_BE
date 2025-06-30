package kr.cseungjoo.userserver.exception;

import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;

public class AlreadyExistUserException extends BasicException {
  public AlreadyExistUserException() {
    super(ErrorCode.ALREADY_EXIST_USER);
  }

  @Override
  public ErrorCode getErrorCode() {
    return ErrorCode.ALREADY_EXIST_USER;
  }
}
