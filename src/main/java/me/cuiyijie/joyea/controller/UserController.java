package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.domain.JoyeaAccessToken;
import me.cuiyijie.joyea.domain.JoyeaUserProfile;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransUserRequest;
import me.cuiyijie.joyea.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(tags = "系统用户模块")
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation(value = "获取用户信息", notes = "通过泛微返回ticket获取用户信息")
    @RequestMapping(value = "authorize", method = RequestMethod.POST)
    public TransBaseResponse authorize(@RequestBody TransUserRequest request) {

        TransBaseResponse response = new TransBaseResponse();

        if(request.isTest()){
            JoyeaUserProfile testProfile = new JoyeaUserProfile();
            testProfile.setId(1);
            testProfile.setLastName("admin");
            testProfile.setMobile("16688888888");
            testProfile.setEmail("admin@joyea.cn");
            response.setObj(testProfile);
        }else{
            JoyeaAccessToken accessToken = userService.getAccessTokenByTicket(request.getTicket());
            JoyeaUserProfile userProfile = userService.getUserInfoByToken(accessToken.getAccessToken());

            response.setObj(userProfile);
        }

        response.setCode("0");

        return response;
    }
}
