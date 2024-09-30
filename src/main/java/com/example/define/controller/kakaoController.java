package com.example.define.controller;

import com.example.define.kakao.KakaoUserResponse;
import com.example.define.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/oauth/kakao")
public class kakaoController {
    // 인가코드 발급
    @GetMapping("/callback")
    public void callbackkakao(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        System.out.println(code);
        String accessTokenFromKakao = KakaoLoginService.getAccessToken(code);

        String redirectUrl = "http://{//리다이렉트api//}/inputInfo?token=" + accessTokenFromKakao;

        response.sendRedirect(redirectUrl);
    }

    // token -> 포스트매핑으로
    @GetMapping("/auth/kakao/callback")
    public String kakaoLogin(@RequestParam String code) {
        // 1. authorization code로 Access Token을 받아옴
        String accessToken = KakaoLoginService.getAccessToken(code);

        // 2. Access Token을 이용해 사용자 정보를 받아옴
        KakaoUserResponse userInfo = KakaoLoginService.getKakaoUserInfo(accessToken);

        // 3. 필요한 로직 처리 (회원 가입/로그인 처리)
        // (예: DB에 사용자 정보를 저장하거나, JWT를 발급하여 클라이언트에게 전달)

        //return "카카오 로그인 성공! 사용자 정보: " + userInfo.getKakaoAccount().getEmail();
        return "카카오 로그인 성공! 사용자 고유 ID: " + userInfo;
    }

}
