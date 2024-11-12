package com.example.define.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    public Long user_code;
    private String kakao_code;

    public UserResponseDto(Long user_code, String kakao_code) {
        this.user_code = user_code;
        this.kakao_code = kakao_code;
    }
}
