package com.baobao.baobaoserver.client;

public record KakaoAccount(
    long id,
    String connectedAt,
    KakaoAccountProfile kakaoAccount
) {
    public record KakaoAccountProfile(
            boolean profileNicknameNeedsAgreement,
            boolean profileImageNeedsAgreement,
            Profile profile,
            boolean nameNeedsAgreement,
            boolean hasEmail,
            boolean emailNeedsAgreement,
            boolean isEmailValid,
            boolean isEmailVerified,
            String email
    ) {
        public record Profile(
            String nickname,
            boolean isDefaultNickname
        ) {}
    }
}
