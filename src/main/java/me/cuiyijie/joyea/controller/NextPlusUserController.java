package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.config.Constants;
import me.cuiyijie.joyea.dao.main.NextPlusUserProfileDao;
import me.cuiyijie.joyea.domain.Department;
import me.cuiyijie.joyea.model.User;
import me.cuiyijie.joyea.pojo.*;
import me.cuiyijie.joyea.pojo.request.TransJoyeaPersonRequest;
import me.cuiyijie.joyea.pojo.request.TransNextPlusUserRequest;
import me.cuiyijie.joyea.service.INextPlusService;
import me.cuiyijie.joyea.service.IUserService;
import me.cuiyijie.joyea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("nextplus")
@Api(tags = "系统用户模块-NextPlus用户体系")
public class NextPlusUserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    private INextPlusService nextPlusService;

    @Autowired
    private UserService userService;

    @Autowired
    private NextPlusUserProfileDao nextPlusUserProfileDao;

    @Autowired
    RestTemplate restTemplate;

//    @ApiOperation(value = "获取用户信息", notes = "通过泛微返回ticket获取用户信息")
//    @RequestMapping(value = "authorize", method = RequestMethod.POST)
//    public TransBaseResponse authorize(@RequestBody TransUserRequest request) {
//
//        TransBaseResponse response = new TransBaseResponse();
//
//        if (request.isTest()) {
//            JoyeaUserProfile testProfile = new JoyeaUserProfile();
//            testProfile.setId(1);
//            testProfile.setLastName("admin");
//            testProfile.setMobile("16688888888");
//            testProfile.setEmail("admin@joyea.cn");
//            response.setObj(testProfile);
//        } else {
//            JoyeaAccessToken accessToken = userService.getAccessTokenByTicket(request.getTicket());
//            JoyeaUserProfile userProfile = userService.getUserInfoByToken(accessToken.getAccessToken());
//
//            response.setObj(userProfile);
//        }
//
//        response.setCode("0");
//
//        return response;
//    }

    @ApiOperation(value = "获取next+的调用桥的ticket", notes = "获取next+的调用桥的ticket")
    @RequestMapping(value = "ticket", method = RequestMethod.POST)
    public TransBaseResponse getNextPTicket(@RequestBody TransNextPlusUserRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        Map<String, String> getAccessTokenParams = new HashMap<String, String>();
        getAccessTokenParams.put("clientId", Constants.getNextPlusClientId());
        getAccessTokenParams.put("clientSecret", Constants.getNextPlusClientSecret());

        HttpEntity<NextPlusAccessTokenResp> accessTokenResp = restTemplate.postForEntity(Constants.NEXT_PLUS_ACCESS_TOKEN_URL, getAccessTokenParams, NextPlusAccessTokenResp.class);


        //创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessTokenResp.getBody().getAccessToken());

        String ticketRequestParamStr = String.format("{\"accessToken\":\"%s\"}", accessTokenResp.getBody().getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(ticketRequestParamStr, headers);
        ResponseEntity<NextPlusTicketResp> responseEntity = restTemplate.postForEntity(Constants.NEXT_PLUS_TICKET_URL, entity, NextPlusTicketResp.class);

        response.setCode("0");
        response.setObj(responseEntity.getBody());

        return response;
    }

    @ApiOperation(value = "获取next+的用户信息", notes = "通过获取到的authCode获取用户信息")
    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public TransBaseResponse getNextPlusProfile(@RequestBody TransNextPlusUserRequest request) {

        TransBaseResponse response = new TransBaseResponse();

        ResponseEntity<NextPlusUserProfile> profileResp = restTemplate.getForEntity(
                String.format("%s%s", Constants.NEXT_PLUS_PROFILE_URL, request.getAuthCode()),
                NextPlusUserProfile.class
        );

        NextPlusUserProfile profile = profileResp.getBody();

        NextPlusUserProfile existProfile = nextPlusUserProfileDao.selectById(profile.getId());
        if(existProfile == null) {
            nextPlusUserProfileDao.insert(profile);
        }else{
            nextPlusUserProfileDao.update(profile);
        }
        response.setCode("0");
        response.setObj(profile);

        return response;
    }

    @ApiOperation(value = "获取nextplus所有用户", notes = "获取nextplus所有用户")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listNextPlusPerson(@RequestBody TransJoyeaPersonRequest request) {
        TransBaseResponse response = new TransBaseResponse();
//        NextPlusTenant nextPlusTenant = nextPlusService.getTenantInfo();
//        if (StringUtil.isNotEmpty(request.getName()) && nextPlusTenant != null) {
//            response.setObj(nextPlusTenant.getMembers().stream().filter(item -> item.getName().contains(request.getName())).collect(Collectors.toList()));
//        } else {
//            response.setObj(nextPlusTenant.getMembers());
//        }
        User user = new User();
        user.setName(request.getName());
        response.setList(userService.listAll(user));

        response.setCode("0");
        return response;
    }

    @RequestMapping(value = "nextplus/notification", method = RequestMethod.POST)
    public TransBaseResponse testNotification(@RequestBody TransJoyeaPersonRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<String> ids = new ArrayList<>();
        ids.add("8a9c8ff35deb01ad015dee4c08920019");
        nextPlusService.sendNotify(ids, "测试推送");
        return response;
    }

    @ApiOperation(value = "获取nextplus所有部门", notes = "获取nextplus所有部门")
    @RequestMapping(value = "department", method = RequestMethod.POST)
    public TransBaseResponse listNextPlusDepartment() {
        TransBaseResponse response = new TransBaseResponse();
//        NextPlusTenant nextPlusTenant = nextPlusService.getTenantInfo();
//        if (StringUtil.isNotEmpty(request.getName()) && nextPlusTenant != null) {
//            response.setObj(nextPlusTenant.getMembers().stream().filter(item -> item.getName().contains(request.getName())).collect(Collectors.toList()));
//        } else {
//            response.setObj(nextPlusTenant.getMembers());
//        }
        List<Department> allDepartment = nextPlusService.getAllDepartment();
        response.setCode("0");
        response.setList(allDepartment);
        return response;
    }
}
