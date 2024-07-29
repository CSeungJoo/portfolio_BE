package kr.cseungjoo.commonmodule.exception;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<BasicResponse.BaseResponse> basicException(BasicException e) {
        return BasicResponse.error(e.getErrorCode());
    }
}