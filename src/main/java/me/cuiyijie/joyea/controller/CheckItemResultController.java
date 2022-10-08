package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemResult;
import me.cuiyijie.joyea.model.vo.CheckItemResultVo;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.service.CheckItemResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("checkItemResult")
@Api(tags = "点检项点检信息模块")
public class CheckItemResultController {

    @Autowired
    private CheckItemResultService checkItemResultService;

    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse listAll(@RequestBody CheckItemResultVo checkItemResultVo) {
        CheckItemResult checkItemResult = new CheckItemResult();
        checkItemResult.setCheckEntryId(checkItemResultVo.getCheckEntryId());
        Page<CheckItemResult> result = checkItemResultService.list(checkItemResult, checkItemResultVo.getPageNum(), checkItemResultVo.getPageSize());
        return new TransBasePageResponse(result);
    }

}
