package com.cwnu.domain.user.entity;

import lombok.Getter;

@Getter
public enum SocialProviderType {

    KAKAO("카카오"),
    NAVER("네이버"),
    GOOGLE("구글");

    private final String description;

    SocialProviderType(String description) {
        this.description = description;
    }

}
