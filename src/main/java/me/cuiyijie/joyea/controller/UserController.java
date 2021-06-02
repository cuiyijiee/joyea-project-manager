package me.cuiyijie.joyea.controller;

import me.cuiyijie.joyea.domain.JoyeaAccessToken;
import me.cuiyijie.joyea.domain.JoyeaUserProfile;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.TransBaseUserRequest;
import me.cuiyijie.joyea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "authorize", method = RequestMethod.POST)
    public TransBaseResponse authorize(@RequestBody TransBaseUserRequest request) {

        TransBaseResponse response = new TransBaseResponse();

        JoyeaAccessToken accessToken = userService.getAccessTokenByTicket(request.getTicket());
        JoyeaUserProfile userProfile = userService.getUserInfoByToken(accessToken.getAccessToken());

        response.setCode("0");
        response.setObj(userProfile);

        return response;
    }
}
