package me.cuiyijie.joyea.controller;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.TransProductRequest;
import me.cuiyijie.joyea.pojo.TransProjectRequest;
import me.cuiyijie.joyea.service.ProjectService;
import me.cuiyijie.joyea.service.ProjectStageService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("project")
@Api(tags = "项目模块")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectStageService projectStageService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProjectRequest request) {
        TransBasePageResponse response = new TransBasePageResponse();
        Project selection = new Project();
        selection.setProjectNumber(request.getProjectNumber());
        selection.setProjectName(request.getProjectName());
        selection.setProjectManager(request.getProjectManager());
        selection.setProjectPrincipal(request.getProjectPrincipal());
        selection.setDepartment(request.getDepartment());
        selection.setProductionNumber(request.getProductionNumber());
        Page<Project> resultList = projectService.list(selection, request.getPageNumber(), request.getPageSize());
        for (int index = 0; index < resultList.size(); index++) {
            Project project = resultList.get(index);
            project.setStageCount(projectStageService.countProjectStage(project.getId()));
        }
        response.setList(resultList.getResult());
        response.setPageNum(resultList.getPageNum());
        response.setPageSize(resultList.getPageSize());
        response.setTotal(resultList.getTotal());
        return response;
    }


    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody TransProjectRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<String> paramsCheck = Lists.newArrayList("projectName:项目名称（projectName）", "projectNumber:项目编号（projectNumber）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            log.error("参数检查错误：" + errorMsg);
            response.setCode("0");
            response.setMsg(errorMsg);
            return response;
        }

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

        List<String> paramsCheck = Lists.newArrayList("projectName:项目名称（projectName）", "projectNumber:项目编号（projectNumber）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            log.error("参数检查错误：" + errorMsg);
            response.setCode("0");
            response.setMsg(errorMsg);
            return response;
        }

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
