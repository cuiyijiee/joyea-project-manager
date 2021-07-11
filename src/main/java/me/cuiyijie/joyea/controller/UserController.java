package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.domain.JoyeaAccessToken;
import me.cuiyijie.joyea.domain.JoyeaUserProfile;
import me.cuiyijie.joyea.pojo.NextPlusAccessTokenResp;
import me.cuiyijie.joyea.pojo.NextPlusTicketResp;
import me.cuiyijie.joyea.pojo.NextPlusUserProfileResp;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransNextPlusUserRequest;
import me.cuiyijie.joyea.pojo.request.TransUserRequest;
import me.cuiyijie.joyea.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Api(tags = "系统用户模块")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${joyea.oauth2.nextplus.clientId}")
    private String nextPlusClientId;

    @Value("${joyea.oauth2.nextplus.clientSecret}")
    private String nextPlusClientSecret;

    private final static String NEXT_PLUS_ACCESS_TOKEN_URL = "https://open.nextxx.cn/openapi/oauth/token";
    private final static String NEXT_PLUS_TICKET_URL = "https://open.nextxx.cn/openapi/oauth/ticket";
    private final static String NEXT_PLUS_PROFILE_URL = "http://hr.joyea.cn:8099/openapi/find-ytm-user-by-code/";

    @ApiOperation(value = "获取用户信息", notes = "通过泛微返回ticket获取用户信息")
    @RequestMapping(value = "authorize", method = RequestMethod.POST)
    public TransBaseResponse authorize(@RequestBody TransUserRequest request) {

        TransBaseResponse response = new TransBaseResponse();

        if (request.isTest()) {
            JoyeaUserProfile testProfile = new JoyeaUserProfile();
            testProfile.setId(1);
            testProfile.setLastName("admin");
            testProfile.setMobile("16688888888");
            testProfile.setEmail("admin@joyea.cn");
            response.setObj(testProfile);
        } else {
            JoyeaAccessToken accessToken = userService.getAccessTokenByTicket(request.getTicket());
            JoyeaUserProfile userProfile = userService.getUserInfoByToken(accessToken.getAccessToken());

            response.setObj(userProfile);
        }

        response.setCode("0");

        return response;
    }

    @ApiOperation(value = "获取next+的调用桥的ticket", notes = "获取next+的调用桥的ticket")
    @RequestMapping(value = "nextplus/ticket", method = RequestMethod.POST)
    public TransBaseResponse getNextPTicket(@RequestBody TransNextPlusUserRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        Map<String, String> getAccessTokenParams = new HashMap<String, String>();
        getAccessTokenParams.put("clientId", nextPlusClientId);
        getAccessTokenParams.put("clientSecret", nextPlusClientSecret);

        HttpEntity<NextPlusAccessTokenResp> accessTokenResp = restTemplate.postForEntity(NEXT_PLUS_ACCESS_TOKEN_URL, getAccessTokenParams, NextPlusAccessTokenResp.class);



        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessTokenResp.getBody().getAccessToken());

        String ticketRequestParamStr = String.format("{\"accessToken\":\"%s\"}",accessTokenResp.getBody().getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(ticketRequestParamStr, headers);
        ResponseEntity<NextPlusTicketResp> responseEntity = restTemplate.postForEntity(NEXT_PLUS_TICKET_URL, entity, NextPlusTicketResp.class);

        response.setCode("0");
        response.setObj(responseEntity.getBody());

        return response;
    }

    @ApiOperation(value = "获取next+的用户信息", notes = "通过获取到的authCode获取用户信息")
    @RequestMapping(value = "nextplus/profile", method = RequestMethod.POST)
    public TransBaseResponse getNextPlusProfile(@RequestBody TransNextPlusUserRequest request){

        TransBaseResponse response = new TransBaseResponse();

        ResponseEntity<NextPlusUserProfileResp> profileResp = restTemplate.getForEntity(
                String.format("%s%s",NEXT_PLUS_PROFILE_URL,request.getAuthCode()),
                NextPlusUserProfileResp.class
        );

        response.setCode("0");
        response.setObj(profileResp.getBody());

        return response;
    }
}
