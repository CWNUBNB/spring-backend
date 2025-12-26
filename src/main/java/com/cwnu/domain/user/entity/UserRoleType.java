package com.cwnu.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleType {
    GUEST("게스트"), HOST("호스트"), ADMIN("관리자");

    private final String description;
}
