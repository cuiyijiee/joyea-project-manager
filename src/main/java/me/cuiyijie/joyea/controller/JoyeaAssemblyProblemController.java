package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.domain.JoyeaAssemblyProblem;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemPageRequest;
import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemSettingsRequest;
import me.cuiyijie.joyea.service.IJoyeaAssemblyProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assemblyProblem")
@Api(tags = "曾经问题模块")
public class JoyeaAssemblyProblemController {

    @Autowired
    IJoyeaAssemblyProblemService joyeaAssemblyProblemService;

    @PostMapping(value = "/findByProject")
    @ApiOperation(value = "查找曾经问题", notes = "传入projectNumber")
    private TransBasePageResponse findByProjectNumber(@RequestBody TransAssemblyProblemPageRequest request) {
        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
        PageInfo<JoyeaAssemblyProblem> result =
                joyeaAssemblyProblemService.selectByProjectNumber(request.getProjectNumber(), request.getPageNum(), request.getPageSize());
        transBasePageResponse.setList(result.getList());
        transBasePageResponse.setPageNum(result.getPageNum());
        transBasePageResponse.setPageSize(result.getPageSize());
        transBasePageResponse.setTotal(result.getTotal());
        return transBasePageResponse;
    }

    @PostMapping(value = "/updateSettings")
    @ApiOperation(value = "更新曾经问题", notes = "根据problemId更新曾经问题")
    private TransBaseResponse updateUrsSettings(@RequestBody TransAssemblyProblemSettingsRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        joyeaAssemblyProblemService.updateProblemSettings(request);
        response.setCode("0");
        return response;
    }

}
