package com.cwnu.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 로그인 실패 시에 호출되는 메서드로 결과로는 401 Unauthorized 상태 코드와 함께 에러 메시지를 JSON 형식으로 응답 본문에 포함합니다.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        log.warn("로그인 실패: {}", exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("{\\\"error\\\":\\\"비밀번호 또는 아이디가 일치하지 않습니다.\\\"}");

    }
}
