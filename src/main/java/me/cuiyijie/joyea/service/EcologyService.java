package me.cuiyijie.joyea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.exception.SysRuntimeException;
import me.cuiyijie.joyea.model.EasUser;
import me.cuiyijie.joyea.model.EcologyAccessToken;
import me.cuiyijie.joyea.model.EcologyUserProfileResp;
import me.cuiyijie.joyea.model.EasPerson;
import me.cuiyijie.joyea.pojo.NextPlusUserProfileResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 泛微ecology oauth2登录相关接口
 * 1.前端通过跳转到泛微授权界面，
 * 2.授权成功之后会将ticket回调到我们的前端页面，
 * 3.前端将ticket回传给后端，
 * 4.后端通过ticket获取到accessToken，
 * 5.进而获取到用户信息
 *
 * @author cyj976655@gmail.com
 * @date 2022/11/21 21:37
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EcologyService {

    @Value("${joyea.ecology.authorize.url}")
    private String authorizeUrl;

    @Value("${joyea.ecology.token.url}")
    private String accessTokenUrl;

    @Value("${joyea.ecology.profile.url}")
    private String profileUrl;

    @Value("${joyea.ecology.redirect.url}")
    private String redirectUrl;

    @Value("${joyea.ecology.clientId}")
    private String clientId;

    @Value("${joyea.ecology.clientSecret}")
    private String clientSecret;

    private final RestTemplate restTemplate;
    private final EasUserService easUserService;

    public String generateLoginUrl() {
        return String.format("%s?client_id=%s&response_type=code&redirect_uri=%s", authorizeUrl, clientId, redirectUrl);
    }

    public NextPlusUserProfileResp ecologyLogin(String ticket) {
        EcologyAccessToken token = getAccessTokenByTicket(ticket);
        EcologyUserProfileResp profileResp = getUserInfoByToken(token.getAccessToken());

        NextPlusUserProfileResp nextPlusUserProfileResp = new NextPlusUserProfileResp();
        nextPlusUserProfileResp.setName(profileResp.getAttributes().getLastName());
        String workCode = profileResp.getAttributes().getWorkCode();
        EasUser easUser = easUserService.findByNumber(workCode);
        if(easUser == null) {
            log.error("next+登录失败，T_PM_USER表中数据缺失：" + easUser);
            throw new SysRuntimeException("泛微登录失败!");
        }
        nextPlusUserProfileResp.setEasUserId(easUser.getFid());
        nextPlusUserProfileResp.setName(easUser.getFNameL2());
        return nextPlusUserProfileResp;
    }

    public EcologyAccessToken getAccessTokenByTicket(String ticket) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("grant_type", "authorization_code");
        form.add("redirect_uri", redirectUrl);
        form.add("code", ticket);
        EcologyAccessToken result = restTemplate.postForObject(accessTokenUrl, form, EcologyAccessToken.class);
        return result;
    }

    public EcologyUserProfileResp getUserInfoByToken(String token) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("access_token", token);
        EcologyUserProfileResp resp = restTemplate.postForObject(profileUrl, form, EcologyUserProfileResp.class);
        log.error("ecology user info: {}", resp);
        return resp;
    }
}
