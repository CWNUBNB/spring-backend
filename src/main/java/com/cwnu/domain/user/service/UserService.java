package com.cwnu.domain.user.service;

import com.cwnu.domain.user.entity.User;
import com.cwnu.domain.user.repository.UserRepository;
import com.cwnu.global.exception.ApiExceptionCode;
import com.cwnu.global.exception.custom.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new CustomRuntimeException(ApiExceptionCode.USER_NOT_FOUND));
    }

    @Transactional
    public User createUser(User user) {



        return userRepository.save(user);
    }


}
