package com.cwnu.global.exception.custom;

import com.cwnu.global.exception.ApiExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    public CustomRuntimeException(ApiExceptionCode exceptionCode) {
        this.httpStatus = exceptionCode.getHttpStatus();
        this.errorCode = exceptionCode.getErrorCode();
        this.message = exceptionCode.getMessage();
    }
}
