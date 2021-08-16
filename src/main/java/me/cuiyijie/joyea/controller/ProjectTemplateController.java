package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.domain.ProjectTemplate;
import me.cuiyijie.joyea.domain.Sheet;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransProjectRequest;
import me.cuiyijie.joyea.pojo.request.projecttemplate.TransProjectTemplatePageRequest;
import me.cuiyijie.joyea.pojo.request.projecttemplate.TransProjectTemplateRequest;
import me.cuiyijie.joyea.pojo.request.sheet.TransSheetPageRequest;
import me.cuiyijie.joyea.pojo.request.sheet.TransSheetRequest;
import me.cuiyijie.joyea.service.IProjectTemplateService;
import me.cuiyijie.joyea.service.ISheetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projectTemplate")
@Api(tags = "项目模板模块")
public class ProjectTemplateController {


    @Autowired
    private IProjectTemplateService projectTemplateService;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增项目模板", notes = "传入name")
    public TransBaseResponse insertProjectTemplate(@RequestBody TransProjectTemplateRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        if (!StringUtils.hasLength(request.getName()) || !StringUtils.hasLength(request.getCategory())) {
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg("参数name或category为空");
        } else {
            ProjectTemplate projectTemplate = new ProjectTemplate();
            BeanUtils.copyProperties(request, projectTemplate);
            projectTemplateService.insert(projectTemplate);
        }
        return transBaseResponse;
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新项目模板", notes = "传入id和name，category,enabled")
    public TransBaseResponse updatePorjectTemplate(@RequestBody TransProjectTemplateRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        if (request.getId() == null || !StringUtils.hasLength(request.getName()) || !StringUtils.hasLength(request.getCategory())) {
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg("参数id或name或category为空");
        } else {
            ProjectTemplate projectTemplate = new ProjectTemplate();
            BeanUtils.copyProperties(request, projectTemplate);
            projectTemplateService.update(projectTemplate);
            transBaseResponse.setCode("0");
        }
        return transBaseResponse;
    }

    @PostMapping(value = "/find")
    @ApiOperation(value = "查询项目模板", notes = "传入id或name或category")
    public TransBasePageResponse findProjectTemplate(@RequestBody TransProjectTemplatePageRequest request) {
        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
        ProjectTemplate projectTemplate = new ProjectTemplate();
        BeanUtils.copyProperties(request, projectTemplate);
        PageInfo<ProjectTemplate> result = projectTemplateService.findAll(projectTemplate, request.getPageNum(), request.getPageSize());
        transBasePageResponse.setPageSize(result.getPageSize());
        transBasePageResponse.setPageNum(result.getPageNum());
        transBasePageResponse.setTotal(result.getTotal());
        transBasePageResponse.setList(result.getList());
        return transBasePageResponse;
    }


    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除项目模板", notes = "传入id")
    public TransBaseResponse deleteSheet(@RequestBody TransProjectTemplateRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        if (request.getId() == null) {
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg("参数id为空");
        } else {
            ProjectTemplate projectTemplate = new ProjectTemplate();
            BeanUtils.copyProperties(request, projectTemplate);
            projectTemplateService.delete(projectTemplate);
            transBaseResponse.setCode("0");
        }
        return transBaseResponse;
    }

}
