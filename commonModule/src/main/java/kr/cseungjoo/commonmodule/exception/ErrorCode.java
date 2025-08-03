package kr.cseungjoo.commonmodule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ErrorCode {
    //system
    ERROR_CODE_NOT_FOUND("ErrorCode Not Found", HttpStatus.INTERNAL_SERVER_ERROR,"S4040"),
    OPEN_FEIGN_EXCEPTION("서비스간 통신 과정에 문제가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR, "S5000"),
    //auth
    AUTH_FAILED("접근이 거부되었습니다.", HttpStatus.UNAUTHORIZED, "04010"),
    //user
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "14040"),
    ALREADY_EXIST_USER("이미 존재하는 사용자입니다.", HttpStatus.CONFLICT, "14090"),
    LOGIN_FAILURE("로그인에 실패하였습니다.", HttpStatus.UNAUTHORIZED, "14010"),
    //profile
    PROFILE_NOT_FOUND("프로필을 찾을 수 없습니다.", HttpStatus.NOT_FOUND, "24040"),
    PROFILE_ACCESS_DENIED("프로필을 조회할 권한이 없습니다.", HttpStatus.FORBIDDEN, "24030"),
    PROFILE_EXISTS("프로필이 이미 존재합니다.", HttpStatus.CONFLICT, "24090");
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
