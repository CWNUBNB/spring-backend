package com.cwnu.domain.auth.service;

import static com.cwnu.domain.user.entity.UserRoleType.*;

import com.cwnu.domain.user.dto.LoginRequestDto;
import com.cwnu.domain.user.dto.LoginResponseDto;
import com.cwnu.domain.user.dto.SignupRequestDto;

import com.cwnu.domain.user.entity.User;
import com.cwnu.domain.user.entity.UserRoleType;
import com.cwnu.domain.user.repository.UserRepository;
import com.cwnu.global.security.jwt.JwtService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void signup(SignupRequestDto signupRequest) {
        if(userRepository.existsByLoginId(signupRequest.getLoginId())) {
            throw new DuplicateRequestException("이미 사용중인 로그인 ID입니다.");
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new DuplicateRequestException("이미 사용중인 이메일입니다.");
        }

        // 유저 엔티티 생성
        User user = User.builder()
            .loginId(signupRequest.getLoginId())
            .userRoleType(GUEST)
            .password(signupRequest.getPassword())
            .nickname(signupRequest.getName())
            .email(signupRequest.getEmail())
            .build();

        // 비밀번호 인코딩
        user.encodePassword(passwordEncoder);

        // 유저 저장
        userRepository.save(user);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getLoginId(),
                loginRequest.getPassword()
            )
        );

        String loginId = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        String accessToken = jwtService.generateAccessToken(loginId, role);
        String refreshToken = jwtService.generateRefreshToken(loginId);

        return new LoginResponseDto(accessToken, refreshToken);
    }


}
