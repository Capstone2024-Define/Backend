package com.example.define.kakao;

public class KakaoUserResponse {
    private Long id;
    private KakaoAccount kakaoAccount;

    public static class KakaoAccount {
        private Profile profile;
        private String email;

        public static class Profile {
            private String nickname;
            private String profileImageUrl;
        }
    }

    // Getters and Setters
}
