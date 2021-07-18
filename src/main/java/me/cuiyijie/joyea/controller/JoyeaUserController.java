package me.cuiyijie.joyea.controller;

import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import me.cuiyijie.joyea.config.Constants;
import me.cuiyijie.joyea.domain.JoyeaPerson;
import me.cuiyijie.joyea.pojo.NextPlusAccessTokenResp;
import me.cuiyijie.joyea.pojo.NextPlusTenant;
import me.cuiyijie.joyea.pojo.NextPlusTicketResp;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransJoyeaPersonRequest;
import me.cuiyijie.joyea.service.IJoyeaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("joyeaUser")
@Api(tags = "EAS用户模块")
public class JoyeaUserController {

    @Autowired
    private IJoyeaPersonService joyeaPersonService;

    @Autowired
    RestTemplate restTemplate;

    private final static String NEXT_PLUS_ACCESS_TOKEN_URL = "https://open.nextxx.cn/openapi/oauth/token";

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listJoyeaPerson(@RequestBody TransJoyeaPersonRequest request) {

        JoyeaPerson joyeaPerson = new JoyeaPerson();
        joyeaPerson.setName(request.getName());

        List<JoyeaPerson> personList = joyeaPersonService.list(joyeaPerson);

        TransBaseResponse response = new TransBaseResponse();
        response.setCode("0");
        response.setList(personList);

        return response;
    }

    @RequestMapping(value = "nextplus/list", method = RequestMethod.POST)
    public TransBaseResponse listNextPlusPerson(@RequestBody TransJoyeaPersonRequest request) {

        Map<String, String> getAccessTokenParams = new HashMap<String, String>();
        getAccessTokenParams.put("clientId", Constants.getNextPlusClientId());
        getAccessTokenParams.put("clientSecret", Constants.getNextPlusClientSecret());
        HttpEntity<NextPlusAccessTokenResp> accessTokenResp = restTemplate.postForEntity(NEXT_PLUS_ACCESS_TOKEN_URL, getAccessTokenParams, NextPlusAccessTokenResp.class);

        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessTokenResp.getBody().getAccessToken());


        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<NextPlusTenant> responseEntity = restTemplate.exchange(Constants.NEXT_PLUS_JOYEA_TENANT_MEMBER_URL, HttpMethod.GET, entity, NextPlusTenant.class);
        TransBaseResponse response = new TransBaseResponse();


        if(StringUtil.isNotEmpty(request.getName()) && responseEntity.getBody()!= null){
            response.setObj(responseEntity.getBody().getMembers().stream().filter(item -> item.getName().contains(request.getName())).collect(Collectors.toList()));
        }else{
            response.setObj(responseEntity.getBody().getMembers());
        }

        response.setCode("0");

        return response;

    }

}
