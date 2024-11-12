package com.example.define.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserKaKaoLoginResponseDto {
    private HttpStatus status;  // HTTP 상태 코드
    private String token;       // 발급된 JWT 토큰
    private String userId;      // 사용자 고유 ID (또는 이메일 등)

    // 생성자
    public UserKaKaoLoginResponseDto(HttpStatus status, String token, String userId) {
        this.status = status;
        this.token = token;
        this.userId = userId;
    }

}
