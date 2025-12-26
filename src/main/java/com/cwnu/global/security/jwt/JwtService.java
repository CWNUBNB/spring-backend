package com.cwnu.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {
        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private final long accessTokenValidity =  1000 * 60 * 60; // 1시간

    public String generateAccessToken(String loginId, String role){
        return Jwts.builder()
                .setSubject(loginId) // 로그인 ID를 subject로 설정
                .claim("role", role) // 역할 정보 추가
                .setIssuedAt(new Date()) // 발급 시간
                .signWith(getSigningKey()) // 서명 알고리즘 및 비밀 키
                .setExpiration(new java.util.Date(System.currentTimeMillis() + accessTokenValidity)) // 만료 시간
                .compact();
    }


    public String generateRefreshToken(String loginId){
        return Jwts.builder()
                .setSubject(loginId) // 로그인 ID를 subject로 설정
                .setIssuedAt(new Date()) // 발급 시간
                .signWith(getSigningKey()) // 서명 알고리즘 및 비밀 키
                .setExpiration(new java.util.Date(System.currentTimeMillis() + accessTokenValidity * 24 * 7)) // 만료 시간 (7일)
                .compact();
    }

    /**
     * 토큰 파싱
     *
     * @param token JWT 토큰
     * @return Jws<Claims> 객체
     * @throws io.jsonwebtoken.JwtException 토큰이 유효하지 않은 경우 발생
     */
    public Jws<Claims> parseToken(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token);
    }


    /**
     * 토큰 검증 및 로그인 ID 추출
     * 토큰이 유효하면 로그인 ID를 반환하고, 유효하지 않으면 예외를 발생시킴
     * @param token JWT 토큰
     * @return 로그인 ID
     * @throws io.jsonwebtoken.JwtException 토큰이 유효하지 않은 경우 발생
     */
    public String validateToken(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 로그인 ID 반환

    }




}
