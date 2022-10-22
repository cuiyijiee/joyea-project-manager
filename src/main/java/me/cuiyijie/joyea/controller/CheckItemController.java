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

    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse listAll(@RequestBody CheckItemVo checkItemVo) {
        CheckItem checkItem = new CheckItem();
        checkItem.setTaskId(checkItemVo.getTaskId());
        checkItem.setCheckStandard(checkItemVo.getCheckStandard());
        checkItem.setKeyItem(checkItemVo.getKeyItem());
        Page<CheckItem> result = checkItemService.list(checkItem, checkItemVo.getPageNum(), checkItemVo.getPageSize());
        return new TransBasePageResponse(result);
    }

    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "find", method = RequestMethod.POST)
    public TransBaseResponse find(@RequestBody CheckItemVo checkItemVo) {
        CheckItem checkItem = new CheckItem();
        checkItem.setFid(checkItemVo.getFid());
        CheckItem result = checkItemService.find(checkItem);
        return TransBaseResponse.success(result);
    }

    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "count", method = RequestMethod.POST)
    public TransBaseResponse count(@RequestBody CheckItemVo checkItemVo) {
        CheckItem checkItem = new CheckItem();
        checkItem.setTaskId(checkItemVo.getTaskId());
        String result = checkItemService.listCount(checkItem);
        return TransBaseResponse.success(result);
    }
}
