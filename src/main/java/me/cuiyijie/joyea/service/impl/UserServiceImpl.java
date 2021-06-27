package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.domain.JoyeaAccessToken;
import me.cuiyijie.joyea.domain.JoyeaUserProfile;
import me.cuiyijie.joyea.domain.JoyeaUserProfileResp;
import me.cuiyijie.joyea.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements IUserService {

    @Value("${joyea.oauth2.token.url}")
    private String accessTokenUrl;

    @Value("${joyea.oauth2.profile.url}")
    private String profileUrl;

    @Value("${joyea.oauth2.redirect.url}")
    private String redirectUrl;

    @Value("${joyea.oauth2.clientId}")
    private String clientId;

    @Value("${joyea.oauth2.clientSecret}")
    private String clientSecret;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public JoyeaAccessToken getAccessTokenByTicket(String ticket) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("grant_type", "authorization_code");
        form.add("redirect_uri", redirectUrl);
        form.add("code", ticket);
        JoyeaAccessToken result = restTemplate.postForObject(accessTokenUrl, form, JoyeaAccessToken.class);
        return result;
    }

    @Override
    public JoyeaUserProfile getUserInfoByToken(String token) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("access_token", token);
        JoyeaUserProfileResp result = restTemplate.postForObject(profileUrl, form, JoyeaUserProfileResp.class);
        return result.getAttributes();
    }
}
