package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.model.CheckItemResult;
import me.cuiyijie.joyea.model.vo.CheckItemResultVo;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.CheckItemResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("checkItemResult")
@Api(tags = "点检项点检记录信息模块")
@RequiredArgsConstructor
public class CheckItemResultController {

    private final CheckItemResultService checkItemResultService;

    @ApiOperation(value = "获取点检项记录", notes = "获取点检项记录")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse listAll(@RequestBody CheckItemResultVo checkItemResultVo) {
        CheckItemResult checkItemResult = new CheckItemResult();
        checkItemResult.setCfCheckEntryId(checkItemResultVo.getCfCheckEntryId());
        IPage<CheckItemResult> result = checkItemResultService.list(checkItemResult, checkItemResultVo.getPageNum(), checkItemResultVo.getPageSize());
        return new TransBasePageResponse(result);
    }

    @ApiOperation(value = "新增点检记录信息", notes = "新增点检记录信息")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@CurrentUser CurrentUserInfo currentUserInfo,
                                    @RequestBody CheckItemResultVo checkItemResultVo) {
        checkItemResultService.insert(currentUserInfo.getEasUserId(), checkItemResultVo);
        return TransBaseResponse.success(null);
    }
}
