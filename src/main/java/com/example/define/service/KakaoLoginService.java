package com.example.define.service;

import com.example.define.kakao.KakaoTokenResponse;
import com.example.define.kakao.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/*
@RequiredArgsConstructor
@Service
@Slf4j
public class KakaoLoginService {

    @Value("${kakao.client_id}")
    private static String clientid;

    @Value("${kakao.redirect_uri}")
    private static String redirectUri;

    @Value("${kakao.token-url}")
    private String tokenUrl;

    @Value("${kakao.user-info-url}")
    private static String userInfoUrl;

    private static final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";


    public static String getAccessToken(String code) {
        WebClient webClient = WebClient.create(KAUTH_TOKEN_URL_HOST);


        KakaoTokenResponse kakaoTokenResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientid)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("code", code)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .block();

        log.info("Access Token: {}", kakaoTokenResponse.getAccessToken());
        return kakaoTokenResponse.getAccessToken();

    }

    public static KakaoUserResponse getKakaoUserInfo(String accessToken) {
        // Access Token을 사용해 Kakao 사용자 정보를 가져오는 로직
        WebClient webClient = WebClient.create(userInfoUrl);

        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("").build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUserResponse.class)
                .block();

        log.info("User Info: {}", kakaoUserResponse);
        return kakaoUserResponse;
        //return kakaoUserResponse.getId();
    }

}

 */
