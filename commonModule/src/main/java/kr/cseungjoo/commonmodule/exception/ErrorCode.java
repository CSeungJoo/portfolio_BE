package kr.cseungjoo.commonmodule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ErrorCode {
    //system
    ERROR_CODE_NOT_FOUND("ErrorCode Not Found", HttpStatus.INTERNAL_SERVER_ERROR,"S4040"),
    //auth
    AUTH_FAILED("접근이 거부되었습니다.", HttpStatus.UNAUTHORIZED, "04010"),
    //user
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "14040"),
    ALREADY_EXIST_USER("이미 존재하는 사용자입니다.", HttpStatus.CONFLICT, "14090"),
    LOGIN_FAILURE("로그인에 실패하였습니다.", HttpStatus.UNAUTHORIZED, "14010");

    private final String msg;
    private final HttpStatus status;
    private final String code;

    ErrorCode(String msg, HttpStatus status, String code) {
        this.msg = msg;
        this.status = status;
        this.code = code;
    }

    public static ErrorCode find(String code) {
        ErrorCode errorCode = Arrays.stream(ErrorCode.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElseThrow(ErrorCodeNotFoundException::new);

        return errorCode;
    }

}
