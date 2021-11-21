package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.CheckItemTag;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransCheckItemTagRequest;
import me.cuiyijie.joyea.pojo.request.TransPageCheckItemTagRequest;
import me.cuiyijie.joyea.service.CheckItemTagService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Author: yjcui3
 * @Date: 2021/11/19 15:00
 */
@RestController
@RequestMapping("checkItemTag")
@Api(tags = "点检项标签模块")
public class CheckItemTagController {

    private final Logger logger = LoggerFactory.getLogger(CheckItemTagController.class);

    @Autowired
    private CheckItemTagService checkItemTagService;

    @ApiOperation(value = "分页展示全部的tag", notes = "分页展示全部的tag")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBasePageResponse list(@RequestBody TransPageCheckItemTagRequest request) {
        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
        try {
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
            List<CheckItemTag> result = checkItemTagService.list(request);
            transBasePageResponse = new TransBasePageResponse(new PageInfo<>(result));
        } catch (Exception exception) {
            logger.error("查询点检项标签失败:", exception);
            transBasePageResponse.setCode("-1");
            transBasePageResponse.setMsg(exception.getMessage());
        }
        return transBasePageResponse;
    }

    @ApiOperation(value = "新增新的点检项标签", notes = "新增新的点检项标签")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody CheckItemTag request) {
        TransBaseResponse response = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("tagName:标签名称", "tagType:标签类型");
        String errMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errMsg != null) {
            logger.error(errMsg);
            response.setCode("-1");
            response.setMsg(errMsg);
            return response;
        }
        //检查标签是否存在
        CheckItemTag existTags = checkItemTagService.selectByTypeAndName(request.getTagType(), request.getTagName());
        if (existTags != null) {
            logger.error("该标签已经存在！");
            response.setCode("-1");
            response.setMsg("该标签已经存在");
            return response;
        }
        Integer result = checkItemTagService.insert(request);
        if (result > 0) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("插入失败");
        }
        return response;
    }

    @ApiOperation(value = "编辑点检项标签", notes = "编辑点检项标签")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody CheckItemTag request) {
        TransBaseResponse response = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:id");
        String errMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errMsg != null) {
            logger.error(errMsg);
            response.setCode("-1");
            response.setMsg(errMsg);
            return response;
        }
        //检查标签是否存在
        CheckItemTag existTag = checkItemTagService.selectById(request.getId());
        if (existTag == null) {
            logger.error("该标签不存在！");
            response.setCode("-1");
            response.setMsg("该标签不存在！");
            return response;
        }
//        if (existTag.getTagType() == request.getTagType() && existTag.getTagName().equals(request.getTagName())) {
//            logger.error("要更新的内容没有改动！");
//            response.setCode("-1");
//            response.setMsg("要更新的内容没有改动！");
//            return response;
//        }
        //检查标签是否存在
        CheckItemTag existTag1 = checkItemTagService.selectByTypeAndName(request.getTagType(), request.getTagName());
        if (existTag1 != null) {
            logger.error("该标签不存在！");
            response.setCode("-1");
            response.setMsg("该标签不存在！");
            return response;
        }
        Integer result = checkItemTagService.update(request);
        if (result > 0) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("更新失败");
        }
        return response;
    }

    @ApiOperation(value = "删除点检项标签", notes = "删除点检项标签")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody CheckItemTag request) {
        TransBaseResponse response = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:id");
        String errMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errMsg != null) {
            logger.error(errMsg);
            response.setCode("-1");
            response.setMsg(errMsg);
            return response;
        }
        //检查标签是否存在
        CheckItemTag existTag = checkItemTagService.selectById(request.getId());
        if (existTag == null) {
            logger.error("该标签不存在！");
            response.setCode("-1");
            response.setMsg("该标签不存在！");
            return response;
        }
        //检查标签是否被关联
        Integer relNum = checkItemTagService.getTagRelNum(request);
        if (relNum > 0) {
            logger.error("该标签已经被点检项关联，请解除关联之后再进行删除操作！");
            response.setCode("-1");
            response.setMsg("该标签已经被点检项关联！");
            return response;
        }
        Integer result = checkItemTagService.delete(request);
        if (result > 0) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("删除失败！");
        }
        return response;
    }

    @ApiOperation(value = "增加点检项标签关联", notes = "增加点检项标签关联")
    @RequestMapping(value = "addTagRel", method = RequestMethod.POST)
    public TransBaseResponse addCheckItemTagRel(@RequestBody TransCheckItemTagRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:标签id（id）", "checkItemId:点检项ID（checkItemId）");
        String errMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errMsg != null) {
            logger.error(errMsg);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(errMsg);
            return transBaseResponse;
        }
        try {
            Integer count = checkItemTagService.selectRelCount(request.getCheckItemId(), request.getId());
            if (count > 0) {
                logger.error("该标签已经被点检项关联！");
                transBaseResponse.setCode("-1");
                transBaseResponse.setMsg("该标签已经被点检项关联！");
                return transBaseResponse;
            }
            checkItemTagService.addCheckItemTagRel(request.getCheckItemId(), request.getId());
            transBaseResponse.setObj("0");
        } catch (Exception exception) {
            logger.error("关联点检项标签失败：", exception);
            transBaseResponse.setObj("-1");
            transBaseResponse.setMsg(exception.getMessage());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "删除点检项标签关联", notes = "删除点检项标签关联")
    @RequestMapping(value = "deleteTagRel", method = RequestMethod.POST)
    public TransBaseResponse deleteCheckItemTagRel(@RequestBody TransCheckItemTagRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:标签id（id）", "checkItemId:点检项ID（checkItemId）");
        String errMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errMsg != null) {
            logger.error(errMsg);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(errMsg);
            return transBaseResponse;
        }
        try {
            checkItemTagService.deleteCheckItemTagRel(request.getCheckItemId(), request.getId());
            transBaseResponse.setObj("0");
        }catch (Exception exception){
            logger.error("删除关联点检项标签失败：", exception);
            transBaseResponse.setObj("-1");
            transBaseResponse.setMsg(exception.getMessage());
        }
        return transBaseResponse;
    }

}
