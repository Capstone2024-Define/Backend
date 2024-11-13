package com.example.define.service;

import com.example.define.dto.UserKaKaoLoginResponseDto;
import com.example.define.dto.UserResponseDto;
import com.example.define.mapper.UserMapper;
import com.example.define.provider.JwtTokenProvider;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class OAuthService {


    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @Autowired
    private OAuthService(JwtTokenProvider jwtTokenProvider, UserMapper userMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
    }

    // INSERT
    public void insertUser(UserResponseDto userResponseDto) {
        userMapper.insertUser(userResponseDto);
    }

    // SELECT
    public UserResponseDto getUser(String kakao_code) {
        return userMapper.findUserByKaKaoCode(kakao_code);
    }

    public String getRedirectUri() {
        // 환경 변수에서 서버 IP 가져오기
        String serverIp = System.getenv("SERVER_IP");

        if (serverIp == null || serverIp.isEmpty()) {
            throw new IllegalArgumentException("환경 변수 SERVER_IP가 설정되지 않았습니다.");
        }

        // 리다이렉트 URI 설정
        String redirectUri = "http://" + serverIp + ":8080/Login";
        return redirectUri;
    }


    public String getKaKaoAccessToken(String code) {
        String access_token = "";
        String refresh_token = "";
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        String client_id = "5757072cc0c10be2da7715dedd4429d8"; // REST_API_KEY
        String redirect_uri = getRedirectUri();                // 인가코드 받은 RedirectURI

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST parameter stream
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + client_id);
            sb.append("&redirect_uri=" + redirect_uri);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            // code <200>? ok
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode: " + responseCode);
            if (responseCode != 200) {
                throw new IOException("카카오 서버 오류: 응답 코드 " + responseCode);
            }

            // require -> READ response message(json)
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine())!= null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // Gson 라이브러리로 json 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_token);
            System.out.println("refresh_token : " + refresh_token);

            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("카카오 액세스 토큰을 가져오는 데 실패했습니다.", e);
        }
        return access_token;
    }

    public HashMap<String, Object> getUserKakaoInfo(String access_Token) {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode: " + responseCode);

            if (responseCode != 200) {
                throw new IOException("카카오 사용자 정보 조회 실패: 응답 코드 " + responseCode);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine())!= null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String id = element.getAsJsonObject().get("id").getAsString();

            // 추가 필요한 정보들
            /*
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = kakao_account.get("nickname").getAsString();
            if(kakao_account.getAsJsonObject().get("email") != null) {
                String email = kakao_account.getAsJsonObject().get("email").getAsString();
                userInfo.put("email", email);
            }
            userInfo.put("nickname", nickname);
             */

            // 유저 식별할 id니까 필수!
            userInfo.put("id", id);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("사용자 정보를 가져오는 데 실패했습니다: " + e.getMessage());
            throw new RuntimeException("카카오 사용자 정보를 가져오는 데 실패했습니다.", e);
        }
        return userInfo;
    }

    public UserKaKaoLoginResponseDto kakaoLogin(String accessToken) {
        // 1. 카카오 사용자 정보 가져오기 (고유 ID만 필요)
        HashMap<String, Object> userInfo = getUserKakaoInfo(accessToken);
        String kakao_code = userInfo.get("id").toString(); // 카카오 고유 ID

        // 2. DB에서 해당 사용자 정보 조회 (이미 가입된 사용자 확인)
        UserResponseDto userResponseDto = getUser(kakao_code);

        // 3. 사용자 정보가 없으면 회원가입 처리
        if (userResponseDto == null) {
            UserResponseDto newUser = new UserResponseDto(null, kakao_code);
            signUp(newUser);  // 회원가입 메서드 호출
            userResponseDto = getUser(kakao_code);  // 새로 가입된 사용자 정보 조회
        }

        // 4. JWT 토큰 발급 : 고유 ID로 토큰 생성
        String token;
        try {
            token = jwtTokenProvider.createToken(userResponseDto.getUser_code().toString());
        } catch (Exception e) {
            throw new RuntimeException("JWT 토큰 생성에 실패했습니다.", e);
        }

        return new UserKaKaoLoginResponseDto(HttpStatus.OK, token, userResponseDto.getUser_code().toString());
    }

    public Long signUp(UserResponseDto userResponseDto){
        try {
            insertUser(userResponseDto);
            // 저장 후 자동 생성된 user_code 반환
            UserResponseDto savedUser = getUser(userResponseDto.getKakao_code());
            return savedUser.getUser_code();
        } catch (Exception e) {
            System.out.println("사용자 저장 실패: " + e.getMessage());
            throw new RuntimeException("사용자 저장에 실패했습니다.", e);
        }
    }


}
