package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import me.cuiyijie.joyea.domain.CheckItemFormSetting;
import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransCheckItemRequest;
import me.cuiyijie.joyea.service.ICheckItemFormSettingService;
import me.cuiyijie.joyea.service.IJoyeaCheckItemService;
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

    @Autowired
    private IJoyeaCheckItemService joyeaCheckItemService;

    @Autowired
    private ICheckItemFormSettingService checkItemFormSettingService;

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
    public TransBaseResponse getCheckItemSetting(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<CheckItemFormSetting> checkItemFormSettingList = checkItemFormSettingService.listAll(request.getId());
        response.setList(checkItemFormSettingList);
        response.setCode("0");
        return response;
    }

}
