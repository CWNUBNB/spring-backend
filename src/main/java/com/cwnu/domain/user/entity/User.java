package com.cwnu.domain.user.entity;

import com.cwnu.domain.user.dto.UserUpdateRequestDto;
import com.cwnu.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
;



@Entity
@Table(name = "users")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true, length = 50)
    private String loginId;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "is_social")
    private Boolean isSocial;

    @Column(name = "social_provider_type")
    @Enumerated(value = EnumType.STRING)
    private SocialProviderType socialProviderType;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "nickname", nullable = false, length = 100)
    private String nickname;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "user_role_type", nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private UserRoleType userRoleType;

    //** 비즈니스 메서드 **//
    public void updateUser(UserUpdateRequestDto dto){
        this.nickname = dto.getNickname();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
    }

}
