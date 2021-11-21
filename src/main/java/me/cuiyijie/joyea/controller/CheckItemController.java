package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
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

@RestController
@RequestMapping("checkItem")
@Api(tags = "点检项模块")
public class CheckItemController {

    private Logger logger = LoggerFactory.getLogger(CheckItemController.class);

    @Autowired
    private CheckItemService checkItemService;
    @Autowired
    private CheckItemTagService checkItemTagService;


    @ApiOperation(value = "获取点检项", notes = "获取点检项")
    @RequestMapping(value = "listAll", method = RequestMethod.POST)
    public TransBasePageResponse listAll(@RequestBody CheckItemVo checkItemVo) {
        PageHelper.startPage(checkItemVo.getPageNum(),checkItemVo.getPageSize());
        List<CheckItem> result = checkItemService.listAll(checkItemVo);
        return new TransBasePageResponse(new PageInfo<CheckItem>(result));
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
        Integer relCount = checkItemService.selectCheckItemRel(checkItem.getId());
        if (relCount > 0) {
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg("请在工序中解除关联后删除！");
            return transBaseResponse;
        }
        transBaseResponse.setCode(checkItemService.delete(checkItem) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

    @ApiOperation(value = "增加点检项关联", notes = "增加点检项关联")
    @RequestMapping(value = "addCheckItemRel", method = RequestMethod.POST)
    public TransBaseResponse addCheckItemRel(@RequestBody CheckItemVo checkItemVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            if (checkItemVo.getPid() == null) {
                throw new RuntimeException("pid不能为空");
            }
            if (checkItemVo.getId() != null) {
                checkItemService.addCheckItemRel(checkItemVo.getPid(), checkItemVo.getId());
            }
            if (checkItemVo.getIds() != null && checkItemVo.getIds().size() > 0) {
                for (int index = 0; index < checkItemVo.getIds().size(); index++) {
                    int id = checkItemVo.getIds().get(index);
                    checkItemService.addCheckItemRel(checkItemVo.getPid(), id);
                }
            }
        } catch (Exception exception) {
            logger.error("增加点检项关联：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @Transactional
    @ApiOperation(value = "删除点检项关联", notes = "删除点检项关联")
    @RequestMapping(value = "deleteCheckItemRel", method = RequestMethod.POST)
    public TransBaseResponse deleteCheckItemRel(@RequestBody CheckItemVo checkItemVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            if (checkItemVo.getPid() == null) {
                throw new RuntimeException("pid不能为空");
            }
            if (checkItemVo.getId() != null) {
                checkItemService.deleteCheckItemRel(checkItemVo.getPid(), checkItemVo.getId());
            }
            if (checkItemVo.getIds() != null && checkItemVo.getIds().size() > 0) {
                for (int index = 0; index < checkItemVo.getIds().size(); index++) {
                    int id = checkItemVo.getIds().get(index);
                    checkItemService.deleteCheckItemRel(checkItemVo.getPid(), id);
                }
            }
        } catch (Exception exception) {
            logger.error("删除点检项关联：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "更新点检项状态", notes = "更新点检项状态")
    @RequestMapping(value = "updateState", method = RequestMethod.POST)
    public TransBaseResponse updateState(@RequestBody CheckItem checkItem) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:点检项id（id）", "enabled:启用禁用状态（enabled）");
        String errorMsg = CheckParamsUtil.checkAll(checkItem, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setCode("0");
            transBaseResponse.setMsg(errorMsg);
            return transBaseResponse;
        }
        checkItemService.updateState(checkItem);
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }
}
