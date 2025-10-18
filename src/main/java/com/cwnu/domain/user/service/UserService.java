package com.cwnu.domain.user.service;

import com.cwnu.domain.user.dto.UserSingUpRequestDto;
import com.cwnu.domain.user.dto.UserUpdateRequestDto;
import com.cwnu.domain.user.entity.QUser;
import com.cwnu.domain.user.entity.User;
import com.cwnu.domain.user.repository.UserRepository;
import com.cwnu.global.exception.ApiExceptionCode;
import com.cwnu.global.exception.custom.CustomRuntimeException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cwnu.domain.user.entity.QUser.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 ID로 유저 존재 여부 확인
     * @param loginId 로그인 ID
     * @return 존재 여부
     */
    public Boolean existsByLoginId(String loginId){
        return userRepository.existsByLoginId(loginId);
    }

    /**
     * 유저 등록
     * @param userRequestDto 유저 등록 DTO
     * @return 등록된 유저 ID
     */
    @Transactional
    public Long addUser(UserSingUpRequestDto userRequestDto){

        if(existsByLoginId(userRequestDto.getLoginId())){
            throw new CustomRuntimeException(ApiExceptionCode.INVALID_REQUEST);
        }

        User user1 = User.builder()
                .loginId(userRequestDto.getLoginId())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .nickname(userRequestDto.getNickname())
                .email(userRequestDto.getEmail())
                .build();


        return userRepository.save(user1).getId();
    }

    /**
     * 유저 정보 수정
     * @param id 유저 ID
     * @param dto 유저 수정 DTO
     */
    @Transactional
    public void updateUser(Long id, UserUpdateRequestDto dto){

        // 1. 현재 인증된 사용자의 로그인 ID 가져오기
        String sessionLoginId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. 수정하려는 유저 정보 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ApiExceptionCode.USER_NOT_FOUND));

        // 3. 현재 인증된 사용자와 수정하려는 유저가 같은지 확인
        if(!sessionLoginId.equals(user.getLoginId())){
            throw new CustomRuntimeException(ApiExceptionCode.UNAUTHORIZED);
        }

        // 유저 정보 수정
        user.updateUser(dto);
    }

}
