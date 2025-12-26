package com.cwnu.domain.user.service;

import com.cwnu.domain.user.entity.User;
import com.cwnu.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new UsernameNotFoundException("유저 정보를 찾을 수 없습니다. " + loginId));
        return new CustomUserDetails(user);
    }

}
