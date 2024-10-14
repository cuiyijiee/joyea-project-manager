package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("checkItem")
@Api(tags = "点检项模块")
@RequiredArgsConstructor
public class CheckItemController {

    private final CheckItemService checkItemService;

    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse listAll(@RequestBody CheckItemVo checkItemVo, @CurrentUser CurrentUserInfo currentUserInfo) {
        CheckItem checkItem = new CheckItem();
        checkItem.setTaskId(checkItemVo.getTaskId());
        checkItem.setCheckStandard(checkItemVo.getCheckStandard());
        checkItem.setKeyItem(checkItemVo.getKeyItem());
        checkItem.setFinished(checkItemVo.isFinished());
        checkItem.setCfCheckType(checkItemVo.getCfCheckType());
        IPage<CheckItem> result = checkItemService.list(currentUserInfo.getEasUserId(), checkItem,
                checkItemVo.getPageNum(), checkItemVo.getPageSize());
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

    @ApiOperation(value = "获取点检项数量统计", notes = "获取点检项数量统计")
    @RequestMapping(value = "count", method = RequestMethod.POST)
    public TransBaseResponse count(@RequestBody CheckItemVo checkItemVo) {
        CheckItem checkItem = new CheckItem();
        checkItem.setTaskId(checkItemVo.getTaskId());
        String result = checkItemService.listCount(checkItem);
        return TransBaseResponse.success(result);
    }
}
