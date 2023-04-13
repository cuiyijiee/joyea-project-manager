package me.cuiyijie.joyea.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.ProjectStage;
import me.cuiyijie.joyea.model.ProjectStageOperation;
import me.cuiyijie.joyea.model.vo.ProjectStageVo;
import me.cuiyijie.joyea.pojo.TransProjectStageOperationRequest;
import me.cuiyijie.joyea.pojo.TransStageProductRequest;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.CheckItemAnswerService;
import me.cuiyijie.joyea.service.CheckItemService;
import me.cuiyijie.joyea.service.ProjectStageService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cuiyijie
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
                "checkStageType:检验阶段（checkStageType）",
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

//    @ApiOperation(value = "更新点检阶段", notes = "更新点检阶段")
//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public TransBaseResponse update(@RequestBody ProjectStage projectStage) {
//        TransBaseResponse transBaseResponse = new TransBaseResponse();
//        List<String> paramsCheck = Lists.newArrayList("projectId:项目id（projectId）",
//                "checkStageType:检验阶段（checkStageType）",
//                "containsProject:是否包含项目（containsProject）");
//        String errorMsg = CheckParamsUtil.checkAll(projectStage, paramsCheck, null, null);
//        if (errorMsg != null) {
//            return TransBaseResponse.failed(errorMsg);
//        }
//        if (!projectStage.getContainsProject() && (projectStage.getProducts() == null || projectStage.getProducts().size() == 0)) {
//            return TransBaseResponse.failed("待更新阶段不包含项目或阶段！");
//        }
//        try {
//            projectStageService.update(projectStage);
//        } catch (Exception exception) {
//            log.error("更新点检阶段失败：" + exception.getMessage());
//            return TransBaseResponse.failed("更新点检阶段失败：" + exception.getMessage());
//        }
//        return transBaseResponse;
//    }

    @ApiOperation(value = "查找点检阶段", notes = "查找点检阶段")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse list(@RequestBody ProjectStageVo projectStageVo) {
        return null;
    }

    @ApiOperation(value = "删除点检阶段", notes = "删除点检阶段")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody ProjectStage projectStage) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:阶段id（id）");
        String errorMsg = CheckParamsUtil.checkAll(projectStage, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        try {
            projectStageService.delete(projectStage);
        } catch (Exception exception) {
            log.error("删除点检阶段失败：" + exception.getMessage());
            return TransBaseResponse.failed("删除点检阶段失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "产品新增工序", notes = "产品新增工序")
    @RequestMapping(value = "addProductOperation", method = RequestMethod.POST)
    public TransBaseResponse addProductOperation(@RequestBody ProjectStageOperation projectStageOperation) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("stageRelId:阶段-产品关联ID（stageRelId）", "parentId:工序父文件夹id（parentId）", "operationId:工序id（operationId）");
        String errorMsg = CheckParamsUtil.checkAll(projectStageOperation, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        try {
            projectStageService.addProductOperation(projectStageOperation);
        } catch (Exception exception) {
            log.error("新增项目阶段工序失败：" + exception.getMessage());
            return TransBaseResponse.failed("新增项目阶段工序失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "产品批量新增工序", notes = "产品批量新增工序")
    @RequestMapping(value = "addBatchProductOperation", method = RequestMethod.POST)
    public TransBaseResponse addBatchProductOperation(@RequestBody List<ProjectStageOperation> projectStageOperations) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        if (projectStageOperations == null || projectStageOperations.size() == 0) {
            return TransBaseResponse.failed("批量新增项目阶段工序失败：待添加工序列表为空");
        }
        try {
            projectStageService.addProductOperations(projectStageOperations);
        } catch (Exception exception) {
            log.error("批量新增项目阶段工序失败：" + exception.getMessage());
            return TransBaseResponse.failed("批量新增项目阶段工序失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "产品删除工序", notes = "产品删除工序")
    @RequestMapping(value = "deleteProductOperation", method = RequestMethod.POST)
    public TransBaseResponse deleteProductOperation(@RequestBody ProjectStageOperation projectStageOperation) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:工序索引id（id）");
        String errorMsg = CheckParamsUtil.checkAll(projectStageOperation, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        try {
            projectStageService.deleteOperation(projectStageOperation);
        } catch (Exception exception) {
            log.error("删除项目阶段工序失败：" + exception.getMessage());
            return TransBaseResponse.failed("删除项目阶段工序失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "列出阶段产品所有工序", notes = "列出阶段产品所有工序，传入stageProductRelId")
    @RequestMapping(value = "listProductOperation", method = RequestMethod.POST)
    public TransBaseResponse listOperation(@RequestBody TransStageProductRequest stageProductRequest) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("stageProductRelId:工序索引id（stageProductRelId）");
        String errorMsg = CheckParamsUtil.checkAll(stageProductRequest, paramsCheck, null, null);
        if (errorMsg != null) {
            return TransBaseResponse.failed(errorMsg);
        }
        try {
        } catch (Exception exception) {
            log.error("列出项目阶段工序失败：" + exception.getMessage());
            return TransBaseResponse.failed("列出项目阶段工序失败：" + exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "列出阶段产品工序下点检项", notes = "列出阶段产品工序下点检项，传入id")
    @RequestMapping(value = "listCheckItem", method = RequestMethod.POST)
    public TransBaseResponse listCheckItems(@RequestBody TransProjectStageOperationRequest projectStageOperation) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        return transBaseResponse;
    }


    @ApiOperation(value = "列出阶段产品工序下点检项", notes = "列出阶段产品工序下点检项，传入id")
    @RequestMapping(value = "preview", method = RequestMethod.POST)
    public TransBaseResponse preview(@RequestBody ProjectStageVo projectStageVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        return transBaseResponse;
    }
}
