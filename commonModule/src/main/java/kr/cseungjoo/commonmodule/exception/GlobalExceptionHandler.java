package kr.cseungjoo.commonmodule.exception;


import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<BasicResponse.BaseResponse> basicException(BasicException e) {
        return BasicResponse.error(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BasicResponse.BaseResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return createCustomErrorResponse(errors, HttpStatus.BAD_REQUEST, BasicResponse.BaseStatus.ERROR, e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BasicResponse.BaseResponse> runtimeException(RuntimeException e) {
        return createCustomErrorResponse("서버에 알 수 없는 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR, BasicResponse.BaseStatus.ERROR, e);
    }

    private ResponseEntity<BasicResponse.BaseResponse> createErrorResponse(ErrorCode errorCode, Exception e) {
        log.error("Exception: {}", e.getClass().getSimpleName(), e);
        return BasicResponse.error(errorCode);
    }

    private ResponseEntity<BasicResponse.BaseResponse> createCustomErrorResponse(String message, HttpStatus status,  BasicResponse.BaseStatus baseStatus, Exception e) {
        log.error("Exception: {}", e.getClass().getSimpleName(), e);
        return BasicResponse.customStatus(message, status, baseStatus);
    }
    private ResponseEntity<BasicResponse.BaseResponse> createCustomErrorResponse(Object message, HttpStatus status,  BasicResponse.BaseStatus baseStatus, Exception e) {
        log.error("Exception: {}", e.getClass().getSimpleName(), e);
        return BasicResponse.customStatus(message, status, baseStatus);
    }
}
