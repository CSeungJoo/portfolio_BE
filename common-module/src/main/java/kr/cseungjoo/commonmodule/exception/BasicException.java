package kr.cseungjoo.commonmodule.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasicException extends RuntimeException {

    private final ErrorCode errorCode;
}