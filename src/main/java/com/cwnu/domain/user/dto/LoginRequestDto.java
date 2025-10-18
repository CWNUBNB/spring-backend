package com.cwnu.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "{user.loginId.NotBlank}")
    @Schema(description = "로그인 아이디", example = "user123")
    private String loginId;

    @NotBlank(message = "{user.password.NotBlank}")
    @Schema(description = "로그인 비밀번호", example = "password123!")
    private String password;

}
