package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.CheckItemService;
import me.cuiyijie.joyea.service.CheckItemTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public TransBaseResponse listAll(@RequestBody CheckItemVo checkItemVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode("0");
        transBaseResponse.setList(checkItemService.listAll(checkItemVo));
        return transBaseResponse;
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
            logger.error("获取全部根节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "增加产品或工序tag", notes = "增加产品或工序tag")
    @RequestMapping(value = "addTag", method = RequestMethod.POST)
    public TransBaseResponse addTag(@RequestBody CheckItemTag checkItemTag) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            checkItemTagService.insert(checkItemTag);
        } catch (Exception exception) {
            logger.error("获取全部根节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

}
