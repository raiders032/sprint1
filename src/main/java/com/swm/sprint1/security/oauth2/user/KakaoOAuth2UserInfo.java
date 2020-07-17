package com.swm.sprint1.security.oauth2.user;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return ((Map<String, Object>) attributes.get("properties")).get("nickname").toString();
    }

    @Override
    public String getEmail() {
        return ((Map<String, Object>) attributes.get("kakao_account")).get("email").toString();
    }

    @Override
    public String getImageUrl() {
        return ((Map<String, Object>) attributes.get("properties")).get("profile_image").toString();
    }
}
