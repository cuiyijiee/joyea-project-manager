package me.cuiyijie.joyea.controller;

import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.pojo.request.TransCheckItemRequest;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.IJoyeaCheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("checkItem")
public class JoyeaCheckItemController {

    @Autowired
    private IJoyeaCheckItemService joyeaCheckItemService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listByManufactureOrderId(@RequestBody TransCheckItemRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        if (!StringUtils.hasLength(request.getOperationId()) || !StringUtils.hasLength(request.getOperationNo())){
            response.setCode("-1");
            response.setMsg("请求参数operationId或operationNo为空");
            return response;
        }

        List<JoyeaCheckItem> operations = joyeaCheckItemService.findByOperationIdAndNo(request.getOperationId(), request.getOperationNo());

        response.setList(operations);
        response.setCode("0");
        return response;
    }

}
