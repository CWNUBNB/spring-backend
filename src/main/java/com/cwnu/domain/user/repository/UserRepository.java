package com.cwnu.domain.user.repository;

import com.cwnu.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ID로 유저 조회
    Optional<User> findUserById(Long id);

    // 로그인 ID로 유저 존재 여부 확인
    Boolean existsByLoginId(String loginId);

    // 로그인 ID로 유저 조회
    Optional<User> findByLoginId(String loginId);
}
