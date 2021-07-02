package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.dao.joyea.ProjectDao;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransProjectRequest;
import me.cuiyijie.joyea.pojo.vo.JoyeaProjectVO;
import me.cuiyijie.joyea.service.IJoyeaOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("project")
@Api(tags = "EAS项目模块")
public class JoyeaProjectController {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    IJoyeaOperationService joyeaManufactureTaskService;

    @ApiOperation(value = "获取用户信息", notes = "通过泛微返回ticket获取用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "projectNumber", value = "项目名称", required = false, paramType = "form")
    )
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse getProjectList(@RequestBody TransProjectRequest request) {

        Map<String, Object> requestParams = new HashMap<>();
        if (StringUtils.hasLength(request.getProjectNumber())) {
            requestParams.put("projectNumber", request.getProjectNumber());
        }
        if (StringUtils.hasLength(request.getMaterialNumber())) {
            requestParams.put("materialNumber", request.getMaterialNumber());
        }
        if (StringUtils.hasLength(request.getManufactureNumber())) {
            requestParams.put("manufactureNumber", request.getManufactureNumber());
        }
        if (StringUtils.hasLength(request.getTaskCategory())) {
            requestParams.put("taskCategory", request.getTaskCategory());
        }
        if (StringUtils.hasLength(request.getTaskStartTime())) {
            Date startTime = new Date(Long.parseLong(request.getTaskStartTime()));
            requestParams.put("taskStartTime", startTime);
        }
        if (StringUtils.hasLength(request.getTaskEndTime())) {
            Date endTime = new Date(Long.parseLong(request.getTaskEndTime()));
            requestParams.put("taskEndTime", endTime);
        }

        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<JoyeaProjectVO> result = projectDao.selectProject(requestParams);
        return new TransBasePageResponse(new PageInfo<>(result));
    }
}
