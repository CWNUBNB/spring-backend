package com.cwnu.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSingUpRequestDto {

    @NotBlank(message = "{user.loginId.NotBlank}")
    @Schema(example = "abc123", description = "유저 아이디")
    private String loginId;

    @NotBlank(message = "{user.password.NotBlank}")
    @Schema(example = "password123!", description = "유저 비밀번호")
    private String password;

    @Schema(example = "John Doe", description = "유저 닉네임")
    private String nickname;

    @Schema(example = "example@naver.com", description = "유저 이메일")
    private String email;

}
