package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template")
@Api(tags = "模板模块")
public class TemplateController {

    @Autowired
    private TemplateService templateService;


    @ApiOperation(value = "获取全部节点", notes = "获取全部节点")
    @RequestMapping(value = "listAll", method = RequestMethod.POST)
    public TransBaseResponse listAll() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode("0");
        transBaseResponse.setList(templateService.listAll());
        return transBaseResponse;
    }

    @ApiOperation(value = "新增节点", notes = "新增节点")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody Template template) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode(templateService.insert(template) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

    @ApiOperation(value = "获取全部节点", notes = "获取全部节点")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody Template template) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode(templateService.update(template) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

    @ApiOperation(value = "获取全部节点", notes = "获取全部节点")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody Template template) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode(templateService.delete(template) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

}
