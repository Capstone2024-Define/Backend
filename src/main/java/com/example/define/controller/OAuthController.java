package com.example.define.controller;

import com.example.define.dto.UserKaKaoLoginResponseDto;
import com.example.define.service.OAuthService;
import com.example.define.service.PrntCheckService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OAuthController {
    private OAuthService oauthService;

    //@Autowired
    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/exist")
    public int existUser(@RequestParam int user_code) {
        return oauthService.existUser(user_code);
    }

    @GetMapping("/Login")
    public ResponseEntity<UserKaKaoLoginResponseDto> kakaoCallback(/*@ApiParam(value = "kakao auth code", required = true)*/
                              @RequestParam String code, HttpSession session) { // session 추가
        String accessToken = oauthService.getKaKaoAccessToken(code);
        UserKaKaoLoginResponseDto responseDto = oauthService.kakaoLogin(accessToken, session);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/Logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
