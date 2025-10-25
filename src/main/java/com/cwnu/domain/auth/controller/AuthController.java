package com.cwnu.domain.auth.controller;

import com.cwnu.domain.auth.service.AuthService;
import com.cwnu.domain.user.dto.LoginRequestDto;
import com.cwnu.domain.user.dto.LoginResponseDto;
import com.cwnu.domain.user.dto.SignupRequestDto;
import com.cwnu.global.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "00. 인증/인가 API", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", description = "회원가입 요청을 처리하고 새 사용자를 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<Void>> signup(
        @Schema(description = "회원가입 요청 본문")
        @Valid @RequestBody SignupRequestDto signupRequest) {
        authService.signup(signupRequest);
        // 신규 리소스 생성 시 201 Created 반환
        return ResponseDto.created();
    }

    @Operation(summary = "로그인", description = "사용자 로그인 요청을 처리하고 JWT 액세스/리프레시 토큰을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(
        @Schema(description = "로그인 요청 본문")
        @Valid @RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto response = authService.login(loginRequest);
        return ResponseDto.ok(response);
    }
//
//    @Operation(summary = "로그아웃", description = "사용자 로그아웃 처리 및 토큰 무효화 기능을 수행합니다.")
//    @PostMapping("/logout")
//    public ResponseEntity<ResponseDto<Void>> logout(HttpServletRequest request) {
//        authService.logout(request);
//        return ResponseDto.noContent();
//    }





}
