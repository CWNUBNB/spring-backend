package com.cwnu.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiExceptionCode {


    USER_NOT_FOUND("U001", "User not found", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST("R001", "Invalid request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("S001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);;

    private final String errorCode; // 에러 코드
    private final String message; // 에러 메시지
    private final HttpStatus httpStatus; // HTTP 상태 코드

}
