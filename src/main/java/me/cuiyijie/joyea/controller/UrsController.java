package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.domain.JoyeaUrs;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.urs.TransUrsPageRequest;
import me.cuiyijie.joyea.service.IJoyeaUrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("urs")
@Api(tags = "非标urs模块")
public class UrsController {


    @Autowired
    IJoyeaUrsService joyeaUrsService;

    @PostMapping(value = "/findByProject")
    @ApiOperation(value = "查找项目非标URS", notes = "传入projectNumber")
    private TransBasePageResponse findByProjectNumber(@RequestBody TransUrsPageRequest request) {
        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
        PageInfo<JoyeaUrs> result =
                joyeaUrsService.selectByProjectNumber(request.getProjectNumber(), request.getPageNum(), request.getPageSize());
        transBasePageResponse.setList(result.getList());
        transBasePageResponse.setPageNum(result.getPageNum());
        transBasePageResponse.setPageSize(result.getPageSize());
        transBasePageResponse.setTotal(result.getTotal());
        return transBasePageResponse;
    }

}
