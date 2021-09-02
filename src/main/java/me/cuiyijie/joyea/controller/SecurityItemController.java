package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.domain.SecurityItem;
import me.cuiyijie.joyea.domain.SysFileUpload;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemFileRequest;
import me.cuiyijie.joyea.pojo.request.securityitem.TransSecurityItemRequest;
import me.cuiyijie.joyea.service.ICheckItemFileService;
import me.cuiyijie.joyea.service.ISecurityItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("securityItem")
@Api(tags = "安全手册模块")
public class SecurityItemController {

    @Autowired
    ISecurityItemService securityItemService;

    @Autowired
    ICheckItemFileService checkItemFileService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    @ApiOperation(value = "获取项目安全手册信息", notes = "通过projectNumber获取安全手册信息")
    public TransBaseResponse listByManufactureOrderId(@RequestBody TransSecurityItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        if (!StringUtils.hasLength(request.getProjectNumber())) {
            response.setCode("-1");
            response.setMsg("请求参数projectNum为空");
            return response;
        }
        List<SecurityItem> securityItems = securityItemService.find(request.getProjectNumber());
        response.setList(securityItems);
        response.setCode("0");
        return response;
    }

    @ApiOperation(value = "更新项目安全手册信息", notes = "通过id更新安全手册信息，只更新有值的参数，如果是null或空值则不更新")
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

    @ApiOperation(value = "新增项目安全手册信息", notes = "新增一个项目得安全手册")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody TransSecurityItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        SecurityItem securityItem = new SecurityItem();
        BeanUtils.copyProperties(request, securityItem);

        Integer result = securityItemService.add(securityItem);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }

        return response;
    }

    @ApiOperation(value = "删除目安全手册信息", notes = "通过ID删除一个项目得安全手册")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody TransSecurityItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        SecurityItem securityItem = new SecurityItem();
        BeanUtils.copyProperties(request, securityItem);

        Integer result = securityItemService.delete(securityItem);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }

        return response;
    }


    @ApiOperation(value = "增加安全手册附件", notes = "增加安全手册附件")
    @RequestMapping(value = "file/add", method = RequestMethod.POST)
    public TransBaseResponse addFile(@RequestBody TransAssemblyProblemFileRequest request) {

        TransBaseResponse response = new TransBaseResponse();

        Integer result = checkItemFileService.insert(request.getProjectNumber(), request.getFileId(),request.getFileType(), UserFileType.SecurityManual);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("添加出错");
        }

        return response;
    }

    @ApiOperation(value = "获取安全手册所有上传附件", notes = "获取安全手册所有上传附件")
    @RequestMapping(value = "file/list", method = RequestMethod.POST)
    public TransBaseResponse getAllFile(@RequestBody TransAssemblyProblemFileRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<SysFileUpload> result = checkItemFileService.selectByCheckItemId(request.getProjectNumber(), request.getFileType(), UserFileType.SecurityManual);

        response.setList(result);
        response.setCode("0");

        return response;
    }

    @ApiOperation(value = "删除安全手册指定附件", notes = "删除安全手册指定附件")
    @RequestMapping(value = "file/delete", method = RequestMethod.POST)
    public TransBaseResponse deleteFile(@RequestBody TransAssemblyProblemFileRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        Integer result = checkItemFileService.delete(request.getId());
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("删除出现错误");
        }
        return response;
    }

}
