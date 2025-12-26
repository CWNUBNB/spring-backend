package com.cwnu.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @Schema(description = "회원가입 아이디", example = "newuser123")
    @NotBlank(message = "{user.loginId.NotBlank}")
    private String loginId;

    @Schema(description = "회원가입 비밀번호", example = "newpassword123!")
    @NotBlank(message = "{user.password.NotBlank}")
    private String password;

    @Schema(description = "회원 이름", example = "홍길동")
    @NotBlank(message = "{user.name.NotBlank}")
    private String name;

    @Schema(description = "회원 이메일", example = "example@example.com")
    @Email(message = "{user.email.Valid}")
    private String email;
    
}
