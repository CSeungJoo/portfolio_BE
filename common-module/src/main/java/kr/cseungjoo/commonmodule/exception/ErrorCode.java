package kr.cseungjoo.commonmodule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    JOB_CATEGORY_NOT_FOUND("카테고리를 찾을수 없습니다.", HttpStatus.NOT_FOUND, "7404");

    private final String msg;
    private final HttpStatus status;
    private final String code;

    ErrorCode(String msg, HttpStatus status, String code) {
        this.msg = msg;
        this.status = status;
        this.code = code;
    }

}
