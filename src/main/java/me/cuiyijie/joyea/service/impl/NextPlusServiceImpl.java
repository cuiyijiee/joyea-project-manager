package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.config.Constants;
import me.cuiyijie.joyea.pojo.NextPlusAccessTokenResp;
import me.cuiyijie.joyea.pojo.NextPlusNotificationRequest;
import me.cuiyijie.joyea.pojo.NextPlusTenant;
import me.cuiyijie.joyea.service.INextPlusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NextPlusServiceImpl implements INextPlusService {

    private final static Logger LOGGER = LoggerFactory.getLogger(NextPlusServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAccessToken() {
        Map<String, String> getAccessTokenParams = new HashMap<>();
        getAccessTokenParams.put("clientId", Constants.getNextPlusClientId());
        getAccessTokenParams.put("clientSecret", Constants.getNextPlusClientSecret());
        HttpEntity<NextPlusAccessTokenResp> accessTokenResp = restTemplate.postForEntity(Constants.NEXT_PLUS_ACCESS_TOKEN_URL,
                getAccessTokenParams, NextPlusAccessTokenResp.class);
        return accessTokenResp.getBody().getAccessToken();
    }

    @Override
    public NextPlusTenant getTenantInfo() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<NextPlusTenant> responseEntity = restTemplate.exchange(Constants.NEXT_PLUS_JOYEA_TENANT_MEMBER_URL, HttpMethod.GET, entity, NextPlusTenant.class);

        return responseEntity.getBody();
    }

    @Override
    public void sendNotify(List<String> ids, String content) {
        NextPlusNotificationRequest request = new NextPlusNotificationRequest();
        NextPlusNotificationRequest.NextPlusNotificationMessage message = new NextPlusNotificationRequest.NextPlusNotificationMessage();
        message.setContent(content);
        message.setTitle(content);
        message.setSubTitle(content);
        message.setLinkUrl("");
        request.setMessage(message);
        request.setEAppId("2c948b3375315f5a017673df99be00bc");
        request.setTargetIds(ids);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + getAccessToken());
        HttpEntity<NextPlusNotificationRequest> entity = new HttpEntity<>(request, headers);

        //restTemplate.postForEntity(Constants.NEXT_PLUS_SEND_NOTIFICATION_URL, entity, String.class);
        ResponseEntity<String> response = restTemplate.postForEntity(Constants.NEXT_PLUS_SEND_NOTIFICATION_URL, entity, String.class);

        LOGGER.info("invoke nextplus result:{}",response);
    }
}
