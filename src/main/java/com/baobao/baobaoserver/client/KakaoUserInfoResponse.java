package com.baobao.baobaoserver.client;

public record KakaoUserInfoResponse(
        long id,
        String connected_at,
        Properties properties,
        KakaoAccount kakao_account
) {
    public record Properties(
            String nickname
    ) {}

    public record KakaoAccount(
            boolean profile_nickname_needs_agreement,
            boolean profile_image_needs_agreement,
            Profile profile,
            boolean name_needs_agreement,
            boolean has_email,
            boolean email_needs_agreement,
            boolean is_email_valid,
            boolean is_email_verified,
            String email
    ) {
        public record Profile(
                String nickname,
                boolean is_default_nickname
        ) {}
    }
}
