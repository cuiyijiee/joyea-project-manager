package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.EcologyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author cyj976655@gmail.com
 * @date 2022/11/21 21:33
 */
@Slf4j
@RestController
@RequestMapping("ecology")
@Api(tags = "系统用户模块-Ecology用户体系")
@RequiredArgsConstructor
public class EcologyController {

    public final EcologyService ecologyService;

    @ApiOperation(value = "获取ecology认证地址", notes = "获取ecology认证地址")
    @RequestMapping(value = "authUrl", method = RequestMethod.POST)
    public TransBaseResponse getAuthUrl() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode("0");
        transBaseResponse.setObj(ecologyService.generateLoginUrl());
        return transBaseResponse;
    }

    @ApiOperation(value = "获取ecology用户信息", notes = "获取ecology用户信息")
    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public TransBaseResponse ecologyLogin(@PathParam("ticket") String ticket) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode("0");
        transBaseResponse.setObj(ecologyService.ecologyLogin(ticket));
        return transBaseResponse;
    }
}
