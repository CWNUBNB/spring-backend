package com.cwnu.global.security.jwt;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 헤더에 토큰이 없거나 Bearer로 시작하지 않으면 다음 필터로 이동
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 인증인 필요한 요청만 토큰 검증을 진행합니다.
        try {
            String token = header.substring(7);
            String loginId = jwtService.validateToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

            // 인증 정보 설정
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

            // 요청 세부 정보 설정
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (JwtException | IllegalArgumentException e) {
            // 토큰이 유효하지 않거나 예외가 발생한 경우, 인증 정보를 제거
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        // 로그인, 회원가입, Swagger, H2-console 등은 필터 예외 처리
        return path.startsWith("/api/auth/login")
            || path.startsWith("/api/auth/signup")
            || path.startsWith("/swagger")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/h2-console");
    }

}
