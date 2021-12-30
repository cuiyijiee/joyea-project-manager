package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.Sheet;
import me.cuiyijie.joyea.model.SheetColumn;
import me.cuiyijie.joyea.model.SheetData;
import me.cuiyijie.joyea.model.vo.SheetColumnVo;
import me.cuiyijie.joyea.pojo.request.*;
import me.cuiyijie.joyea.service.SheetService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/24 16:28
 */
@RestController
@RequestMapping("checkItemSheet")
@Api(tags = "点检项表格模块")
public class CheckItemSheetController {


    private static final Logger logger = LoggerFactory.getLogger(CheckItemSheetController.class);

    @Autowired
    private SheetService sheetService;

    @ApiOperation(value = "获取所有的表格分类", notes = "获取所有的表格分类")
    @RequestMapping(value = "listAllCategory", method = RequestMethod.POST)
    public TransBaseResponse listAllCategory(@RequestBody TransSheetRequest request) {
        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
        try {

            List<String> categories = sheetService.listAllCategory();
            transBasePageResponse.setList(categories);
            transBasePageResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("获取所有的表格分类失败：", exception);
            transBasePageResponse.setMsg("获取所有的表格分类失败：" + exception.getMessage());
            transBasePageResponse.setCode("-1");
            return transBasePageResponse;
        }
        return transBasePageResponse;
    }


    @ApiOperation(value = "获取所有的表格", notes = "获取所有的表格")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransSheetRequest request) {
        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
        try {

            PageInfo<Sheet> sheets = sheetService.findAll(request, request.getPageNum(), request.getPageSize());
            transBasePageResponse = new TransBasePageResponse(sheets);
            transBasePageResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("列出表格失败：", exception);
            transBasePageResponse.setMsg("列出表格失败：" + exception.getMessage());
            transBasePageResponse.setCode("-1");
            return transBasePageResponse;
        }
        return transBasePageResponse;
    }


    @ApiOperation(value = "新增表格", notes = "新增表格")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody TransSheetRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();

        List<String> paramsCheck = Lists.newArrayList(
                "name:表格名称（name）",
                "category:表格分类（category）"
                );
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }

        try {

            sheetService.insert(request);
            transBaseResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("新增表格失败：", exception);
            transBaseResponse.setMsg("新增表格失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "更新表格", notes = "更新表格")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody TransSheetRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();

        List<String> paramsCheck = Lists.newArrayList(
                "id:表格ID（id）",
                "name:表格名称（name）",
                "category:表格分类（category）"
        );
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }

        try {

            sheetService.update(request);
            transBaseResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("更新表格失败：", exception);
            transBaseResponse.setMsg("更新表格失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "删除表格", notes = "删除表格")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody TransSheetRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();

        List<String> paramsCheck = Lists.newArrayList(
                "id:表格ID（id）"
        );
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }

        try {

            sheetService.delete(request);
            transBaseResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("删除表格失败：", exception);
            transBaseResponse.setMsg("删除表格失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }


    @ApiOperation(value = "获取表格所有表头", notes = "获取表格所有表头")
    @RequestMapping(value = "listAllColumn", method = RequestMethod.POST)
    public TransBaseResponse listAllColumn(@RequestBody TransSheetRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:表格ID（id）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        try {

            List<SheetColumn> sheets = sheetService.listAllColumns(request.getId());
            transBaseResponse.setList(sheets);
            transBaseResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("获取表格所有表头失败：", exception);
            transBaseResponse.setMsg("获取表格所有表头失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "获取表格所有表头", notes = "获取表格所有表头")
    @RequestMapping(value = "updateAllColumn", method = RequestMethod.POST)
    public TransBaseResponse updateAllColumn(@RequestBody TransSheetColumnRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("sheetId:表格ID（sheetId）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        try {

            if (request.getSheetColumn() == null || request.getSheetColumn().size() <= 0) {
                throw new RuntimeException("待更新的表头为空！");
            }

            sheetService.updateAllColumns(request.getSheetId(), request.getSheetColumn());
            transBaseResponse.setCode("0");

        } catch (Exception exception) {
            logger.error("获取表格所有表头失败：", exception);
            transBaseResponse.setMsg("获取表格所有表头失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "获取表格所有的数据", notes = "获取表格所有的数据")
    @RequestMapping(value = "listAllSheetData", method = RequestMethod.POST)
    public TransBaseResponse listAllSheetData(@RequestBody SheetData sheetData) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("stageRelId:阶段id（stageRelId）", "sheetId:表格ID（sheetId）");
        String errorMsg = CheckParamsUtil.checkAll(sheetData, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        try {

            List<SheetColumnVo> result = sheetService.listAllData(sheetData);
            transBaseResponse.setList(result);
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            logger.error("列出表格所有数据失败：", exception);
            transBaseResponse.setMsg("列出表格所有数据失败：" + exception);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "更新表格所有的数据", notes = "更新表格所有的数据")
    @RequestMapping(value = "updateSheetData", method = RequestMethod.POST)
    public TransBaseResponse updateSheetData(@RequestBody TransSheetDataRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("stageRelId:阶段id（stageRelId）", "sheetId:表格ID（sheetId）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            transBaseResponse.setMsg(errorMsg);
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        try {

            if (request.getSheetData() == null || request.getSheetData().size() <= 0) {
                throw new RuntimeException("待更新的表格数据为空！");
            }

            sheetService.updateAllData(request.getSheetId(), request.getStageRelId(), request.getSheetData());
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            logger.error("更新表格数据失败：", exception);
            transBaseResponse.setMsg("更新表格数据失败：" + exception.getMessage());
            transBaseResponse.setCode("-1");
            return transBaseResponse;
        }
        return transBaseResponse;
    }


}
