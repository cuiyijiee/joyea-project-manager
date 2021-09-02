package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.domain.CheckItemFormData;
import me.cuiyijie.joyea.domain.CheckItemFormSetting;
import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.domain.SysFileUpload;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.*;
import me.cuiyijie.joyea.service.ICheckItemFileService;
import me.cuiyijie.joyea.service.ICheckItemFormDataService;
import me.cuiyijie.joyea.service.ICheckItemFormSettingService;
import me.cuiyijie.joyea.service.IJoyeaCheckItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("checkItem")
@Api(tags = "EAS点检项模块")
public class JoyeaCheckItemController {

    private final static Logger LOGGER = LoggerFactory.getLogger(JoyeaCheckItemController.class);

    @Autowired
    private IJoyeaCheckItemService joyeaCheckItemService;

    @Autowired
    private ICheckItemFormSettingService checkItemFormSettingService;

    @Autowired
    private ICheckItemFormDataService checkItemFormDataService;

    @Autowired
    private ICheckItemFileService checkItemFileService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listByManufactureOrderId(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        if (!StringUtils.hasLength(request.getOperationId()) || !StringUtils.hasLength(request.getOperationNo())) {
            response.setCode("-1");
            response.setMsg("请求参数operationId或operationNo为空");
            return response;
        }
        PageInfo<JoyeaCheckItem> operations = joyeaCheckItemService.findByOperationIdAndNo(request);
        return new TransBasePageResponse(operations);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public TransBaseResponse addCheckItem(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        JoyeaCheckItem joyeaCheckItem = new JoyeaCheckItem();
        BeanUtils.copyProperties(request, joyeaCheckItem);
        joyeaCheckItem.setId(UUID.randomUUID().toString());

        Integer result = joyeaCheckItemService.add(joyeaCheckItem);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }

        return response;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        JoyeaCheckItem joyeaCheckItem = new JoyeaCheckItem();
        BeanUtils.copyProperties(request, joyeaCheckItem);


        Integer result = joyeaCheckItemService.update(joyeaCheckItem);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }

        return response;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        JoyeaCheckItem joyeaCheckItem = new JoyeaCheckItem();
        BeanUtils.copyProperties(request, joyeaCheckItem);

        Integer result = joyeaCheckItemService.delete(joyeaCheckItem);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }

        return response;
    }

    @RequestMapping(value = "form/setting", method = RequestMethod.POST)
    public TransBaseResponse getCheckItemFormSetting(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<CheckItemFormSetting> checkItemFormSettingList = checkItemFormSettingService.listAll(request.getId());
        response.setList(checkItemFormSettingList);
        response.setCode("0");
        return response;
    }

    @RequestMapping(value = "form/data/update", method = RequestMethod.POST)
    public TransBaseResponse updateCheckItemFormData(@RequestBody TransCheckItemFormRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        try {
            checkItemFormDataService.updateRowData(request);
            response.setCode("0");
        } catch (Exception exception) {
            response.setCode("-1");
            response.setMsg("更新操作发生错误：" + exception.getMessage());
            LOGGER.error("更新发生错误：", exception);
        }

        return response;
    }

    @ApiOperation(value = "更新点检项表单列数据", notes = "更新点检项表单列数据")
    @RequestMapping(value = "form/data/column/update")
    public TransBaseResponse updateFormColumnData(@RequestBody TransCheckItemFormColumnRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        try {
            checkItemFormDataService.updateColumnData(request);
            response.setCode("0");
        } catch (Exception exception) {
            response.setCode("-1");
            response.setMsg("更新操作发生错误：" + exception.getMessage());
            LOGGER.error("更新发生错误：", exception);
        }
        return response;
    }

    @ApiOperation(value = "更新点检项表全部数据", notes = "更新点检项表全部数据")
    @RequestMapping(value = "form/data/updateAll", method = RequestMethod.POST)
    public TransBaseResponse updateCheckItemFormAllData(@RequestBody TransCheckItemFormUpdateAllRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        try {
            checkItemFormDataService.updateAllRowData(request);
            response.setCode("0");
        } catch (Exception exception) {
            response.setCode("-1");
            response.setMsg("更新操作发生错误：" + exception.getMessage());
            LOGGER.error("更新发生错误：", exception);
        }
        return response;
    }

    @ApiOperation(value = "删除点检项某一行数据", notes = "删除点检项某一行数据")
    @RequestMapping(value = "form/data/delete", method = RequestMethod.POST)
    public TransBaseResponse deleteCheckItemFormRowData(@RequestBody TransCheckItemFormRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        checkItemFormDataService.deleteRowData(request.getCheckItem(), request.getDataType(), request.getRowIndex());
        response.setCode("0");

        return response;
    }

    @ApiOperation(value = "获取点检项表格的所有数据", notes = "获取点检项表格的所有数据")
    @RequestMapping(value = "form/data", method = RequestMethod.POST)
    public TransBaseResponse getCheckItemFormData(@RequestBody TransCheckItemDataRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<CheckItemFormData> result = checkItemFormDataService.findAll(request.getCheckItemId(), request.getRowIndex(),null, request.getDataType());

        response.setCode("0");
        response.setList(result);

        return response;
    }

    @ApiOperation(value = "获取点检项表格的某一列数据", notes = "获取点检项表格的某一列数据")
    @RequestMapping(value = "form/data/column", method = RequestMethod.POST)
    public TransBaseResponse getCheckItemFormColumnData(@RequestBody TransCheckItemDataRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<CheckItemFormData> result = checkItemFormDataService.findAll(request.getCheckItemId(), null,request.getColumnId(), request.getDataType());

        response.setCode("0");
        response.setList(result);

        return response;
    }

    @ApiOperation(value = "增加点检项附件", notes = "增加点检项附件")
    @RequestMapping(value = "file/add", method = RequestMethod.POST)
    public TransBaseResponse addFile(@RequestBody TransCheckItemFileRequest request) {

        TransBaseResponse response = new TransBaseResponse();

        Integer result = checkItemFileService.insert(request.getCheckItemId(), request.getFileId(),request.getFileType(),UserFileType.QualityManual);

        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("添加出错");
        }
        return response;
    }

    @ApiOperation(value = "获取点检项所有上传附件", notes = "获取点检项所有上传附件")
    @RequestMapping(value = "file/list", method = RequestMethod.POST)
    public TransBaseResponse getAllFile(@RequestBody TransCheckItemFileRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<SysFileUpload> result = checkItemFileService.selectByCheckItemId(request.getCheckItemId(), request.getFileType(), UserFileType.QualityManual);

        response.setList(result);
        response.setCode("0");

        return response;
    }

    @ApiOperation(value = "删除点检项指定附件", notes = "删除点检项指定附件")
    @RequestMapping(value = "file/delete", method = RequestMethod.POST)
    public TransBaseResponse deleteFile(@RequestBody TransCheckItemFileRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        Integer result = checkItemFileService.delete(request.getId());
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
            response.setMsg("删除出现错误");
        }
        return response;
    }

}
