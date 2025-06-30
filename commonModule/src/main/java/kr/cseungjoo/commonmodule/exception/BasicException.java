package kr.cseungjoo.commonmodule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicException extends RuntimeException {
    private final ErrorCode errorCode;
}
