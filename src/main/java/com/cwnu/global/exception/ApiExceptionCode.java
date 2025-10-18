package com.cwnu.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiExceptionCode {


    USER_NOT_FOUND("U001", "해당 유저를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST("R001", "요청 값이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("U002", "권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("S001", "서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR),

    ACCOMMODATION_NOT_FOUND("A001", "해당 숙소를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final String errorCode; // 에러 코드
    private final String message; // 에러 메시지
    private final HttpStatus httpStatus; // HTTP 상태 코드

}
