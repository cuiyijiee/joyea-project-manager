package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.domain.SecurityItem;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.securityitem.TransSecurityItemRequest;
import me.cuiyijie.joyea.service.ISecurityItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("securityItem")
@Api(tags = "安全手册模块")
public class SecurityItemController {

    @Autowired
    ISecurityItemService securityItemService;


    @RequestMapping(value = "get", method = RequestMethod.POST)
    @ApiOperation(value = "获取项目安全手册信息", notes = "通过projectNumber获取安全手册信息")
    public TransBaseResponse listByManufactureOrderId(@RequestBody TransSecurityItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        if (!StringUtils.hasLength(request.getProjectNumber())) {
            response.setCode("-1");
            response.setMsg("请求参数projectNum为空");
            return response;
        }
        SecurityItem securityItem = securityItemService.find(request.getProjectNumber());
        response.setObj(securityItem);
        response.setCode("0");
        return response;
    }

    @ApiOperation(value = "更新项目安全手册信息", notes = "通过projectNumber更新安全手册信息，只更新有值的参数，如果是null或空值则不更新")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody TransSecurityItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        SecurityItem securityItem = new SecurityItem();
        BeanUtils.copyProperties(request, securityItem);

        Integer result = securityItemService.update(securityItem);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }

        return response;
    }

}
