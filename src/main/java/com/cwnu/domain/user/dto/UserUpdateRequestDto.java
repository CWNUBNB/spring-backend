package com.cwnu.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    @Schema(example = "newNickname", description = "유저 닉네임")
    private String nickname;

    @Schema(example = "newemail@naver.com", description = "유저 이메일")
    private String email;

    @Schema(example = "010-1234-5678", description = "유저 전화번호")
    private String phoneNumber;


}
