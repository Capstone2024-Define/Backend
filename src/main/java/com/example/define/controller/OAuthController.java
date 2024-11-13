package com.example.define.controller;

import com.example.define.dto.UserKaKaoLoginResponseDto;
import com.example.define.service.OAuthService;
import com.example.define.service.PrntCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {
    private OAuthService oauthService;

    //@Autowired
    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/Login")
    public ResponseEntity<UserKaKaoLoginResponseDto> kakaoCallback(/*@ApiParam(value = "kakao auth code", required = true)*/
                              @RequestParam String code) {
        String accessToken = oauthService.getKaKaoAccessToken(code);
        UserKaKaoLoginResponseDto responseDto = oauthService.kakaoLogin(accessToken);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
