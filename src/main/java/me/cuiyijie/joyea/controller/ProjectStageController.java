package me.cuiyijie.joyea.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.ProjectStage;
import me.cuiyijie.joyea.model.vo.ProjectStageVo;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.ProjectStageService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/11/30 13:49
 */
@Slf4j
@RestController
@RequestMapping("projectStage")
@Api(tags = "项目阶段点检项模块")
public class ProjectStageController {


    @Autowired
    private ProjectStageService projectStageService;

    @ApiOperation(value = "新增点检阶段", notes = "新增点检阶段")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody ProjectStage projectStage) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("projectId:项目id（projectId）",
                "checkStage:检验阶段（checkStage）",
                "containsProject:是否包含项目（containsProject）");
        String errorMsg = CheckParamsUtil.checkAll(projectStage, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        if (!projectStage.getContainsProject() && (projectStage.getProducts() == null || projectStage.getProducts().size() == 0)) {
            return TransBaseResponse.failed("待新增阶段不包含项目或阶段！");
        }
        try {
            projectStageService.insert(projectStage);
        } catch (Exception exception) {
            log.error("新增点检阶段失败：" + exception.getMessage());
            return TransBaseResponse.failed("新增点检阶段失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "更新点检阶段", notes = "更新点检阶段")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody ProjectStage projectStage) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("projectId:项目id（projectId）",
                "checkStage:检验阶段（checkStage）",
                "containsProject:是否包含项目（containsProject）");
        String errorMsg = CheckParamsUtil.checkAll(projectStage, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        if (!projectStage.getContainsProject() && (projectStage.getProducts() == null || projectStage.getProducts().size() == 0)) {
            return TransBaseResponse.failed("待更新阶段不包含项目或阶段！");
        }
        try {
            projectStageService.update(projectStage);
        } catch (Exception exception) {
            log.error("更新点检阶段失败：" + exception.getMessage());
            return TransBaseResponse.failed("更新点检阶段失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "查找点检阶段", notes = "查找点检阶段")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse list(@RequestBody ProjectStageVo projectStageVo) {
        return new TransBasePageResponse(projectStageService.list(projectStageVo));
    }

    @ApiOperation(value = "删除点检阶段", notes = "删除点检阶段")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody ProjectStage projectStage){
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:阶段id（id）");
        String errorMsg = CheckParamsUtil.checkAll(projectStage, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        try{
            projectStageService.delete(projectStage);
        }catch (Exception exception){
            log.error("删除点检阶段失败：" + exception.getMessage());
            return TransBaseResponse.failed("删除点检阶段失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "产品新增", notes = "删除点检阶段")
    @RequestMapping(value = "insertProductOperation", method = RequestMethod.POST)
    public TransBaseResponse insertProductOperation(@RequestBody ){
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        return transBaseResponse;
    }


  
}
