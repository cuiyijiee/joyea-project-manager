package me.cuiyijie.joyea.controller;

import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.TransProductRequest;
import me.cuiyijie.joyea.pojo.TransProjectRequest;
import me.cuiyijie.joyea.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
@Api(tags = "项目模块")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProjectRequest request) {
        TransBasePageResponse response = new TransBasePageResponse();
        Project selection = new Project();
        selection.setProjectNumber(request.getProjectNumber());
        Page<Project> resultList = projectService.list(selection, request.getPageNumber(), request.getPageSize());
        response.setList(resultList.getResult());
        response.setPageNum(resultList.getPageNum());
        response.setPageSize(resultList.getPageSize());
        response.setTotal(resultList.getTotal());
        return response;
    }


    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody TransProjectRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        Project selection = new Project();
        BeanUtils.copyProperties(request, selection);
        Integer result = projectService.insert(selection);
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }
        return response;
    }

    @ApiOperation(value = "更新项目", notes = "传入id,其余同insert")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody TransProjectRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        Project selection = new Project();
        BeanUtils.copyProperties(request,selection);
        Integer result = projectService.update(selection);
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }
        return response;
    }

}