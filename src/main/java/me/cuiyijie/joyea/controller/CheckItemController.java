package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemAnswer;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.CheckItemAnswerService;
import me.cuiyijie.joyea.service.CheckItemService;
import me.cuiyijie.joyea.service.CheckItemTagService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("checkItem")
@Api(tags = "点检项模块")
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;
    @Autowired
    private CheckItemAnswerService checkItemAnswerService;

    private final static Logger logger = LoggerFactory.getLogger(CheckItemController.class);


    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse listAll(@RequestBody CheckItemVo checkItemVo) {
        CheckItem checkItem = new CheckItem();
        checkItem.setTaskId(checkItemVo.getTaskId());
        Page<CheckItem> result = checkItemService.list(checkItem, checkItemVo.getPageNum(), checkItemVo.getPageSize());
        return new TransBasePageResponse(result);
    }

    @ApiOperation(value = "新增点检项", notes = "新增点检项")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody CheckItem checkItem) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode(checkItemService.insert(checkItem) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

    @ApiOperation(value = "更新点检项", notes = "更新点检项")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody CheckItem checkItem) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode(checkItemService.update(checkItem) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

    @ApiOperation(value = "删除点检项", notes = "删除点检项")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody CheckItem checkItem) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        return transBaseResponse;
    }

    @ApiOperation(value = "增加点检项关联", notes = "增加点检项关联")
    @RequestMapping(value = "addCheckItemRel", method = RequestMethod.POST)
    public TransBaseResponse addCheckItemRel(@RequestBody CheckItemVo checkItemVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        return transBaseResponse;
    }

    @Transactional
    @ApiOperation(value = "删除点检项关联", notes = "删除点检项关联")
    @RequestMapping(value = "deleteCheckItemRel", method = RequestMethod.POST)
    public TransBaseResponse deleteCheckItemRel(@RequestBody CheckItemVo checkItemVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        return transBaseResponse;
    }

    @ApiOperation(value = "更新点检项状态", notes = "更新点检项状态")
    @RequestMapping(value = "updateState", method = RequestMethod.POST)
    public TransBaseResponse updateState(@RequestBody CheckItem checkItem) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:点检项id（id）", "enabled:启用禁用状态（enabled）");
        String errorMsg = CheckParamsUtil.checkAll(checkItem, paramsCheck, null, null);
        if (errorMsg != null) {
            log.error("参数检查错误：" + errorMsg);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(errorMsg);
            return transBaseResponse;
        }
        try {
            checkItemService.updateState(checkItem);
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("更新点检项状态失败：", exception);
            transBaseResponse.setMsg(exception.getMessage());
            transBaseResponse.setCode("-1");
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "点检项结果录入", notes = "点检项结果录入")
    @RequestMapping(value = "updateResult", method = RequestMethod.POST)
    public TransBaseResponse updateResult(@RequestBody CheckItemAnswer checkItemAnswer) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("stageRelId:阶段-产品关联ID（stageRelId）", "checkItemId:点检项ID（checkItemId）");
        String errorMsg = CheckParamsUtil.checkAll(checkItemAnswer, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(errorMsg);
            return transBaseResponse;
        }

        try {
            checkItemAnswerService.updateResult(checkItemAnswer);
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            logger.error("点检项结果录入失败：", exception);
            transBaseResponse.setMsg("点检项结果录入失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
        }

        return transBaseResponse;
    }

    @ApiOperation(value = "点检项结果查询", notes = "点检项结果查询")
    @RequestMapping(value = "selectResult", method = RequestMethod.POST)
    public TransBaseResponse select(@RequestBody CheckItemAnswer checkItemAnswer) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("stageRelId:阶段-产品关联ID（stageRelId）", "checkItemId:点检项ID（checkItemId）");
        String errorMsg = CheckParamsUtil.checkAll(checkItemAnswer, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(errorMsg);
            return transBaseResponse;
        }

        try {

            CheckItemAnswer result = checkItemAnswerService.select(checkItemAnswer);
            transBaseResponse.setObj(result == null ? new CheckItemAnswer() : result);
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            logger.error("点检项结果查询失败：", exception);
            transBaseResponse.setMsg("点检项结果查询失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
        }

        return transBaseResponse;
    }
}
