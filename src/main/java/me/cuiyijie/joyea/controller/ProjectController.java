package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.model.ProjectSchedule;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransProjectRequest;
import me.cuiyijie.joyea.service.ProjectScheduleService;
import me.cuiyijie.joyea.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("project")
@Api(tags = "项目模块")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectScheduleService projectScheduleService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProjectRequest request, @CurrentUser CurrentUserInfo currentUser) {

        log.info("获取到用户信息：" + currentUser.getEasUserId());

        Project project = new Project();
        project.setProjectName(request.getProjectName());
        Page<Project> projectPage = projectService.list(project, request.getPageNum(), request.getPageSize());
        return new TransBasePageResponse(projectPage);
    }

    @RequestMapping(value = "findSchedule", method = RequestMethod.POST)
    public TransBaseResponse findSchedule(@RequestBody TransProjectRequest request) {
        ProjectSchedule projectSchedule = new ProjectSchedule();
        projectSchedule.setFid(request.getFid());

        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(projectScheduleService.select(projectSchedule));
        transBaseResponse.setCode("0");

        return transBaseResponse;
    }
}
