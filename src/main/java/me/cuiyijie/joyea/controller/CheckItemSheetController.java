package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.Sheet;
import me.cuiyijie.joyea.model.SheetData;
import me.cuiyijie.joyea.model.vo.SheetColumnVo;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.TransSheetDataRequest;
import me.cuiyijie.joyea.pojo.TransSheetRequest;
import me.cuiyijie.joyea.service.impl.SheetService;
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
 * @Author: yjcui3
 * @Date: 2021/12/24 16:28
 */
@RestController
@RequestMapping("checkItemSheet")
@Api(tags = "点检项表格模块")
public class CheckItemSheetController {


    private static final Logger logger = LoggerFactory.getLogger(CheckItemSheetController.class);

    @Autowired
    private SheetService sheetService;

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

            if(request.getSheetData() == null || request.getSheetData().size() <= 0) {
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
