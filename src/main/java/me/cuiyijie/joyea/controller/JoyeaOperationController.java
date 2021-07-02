package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import me.cuiyijie.joyea.domain.JoyeaOperation;
import me.cuiyijie.joyea.pojo.request.TransOperationRequest;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.IJoyeaOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("operation")
@Api(tags = "EAS工序模块")
public class JoyeaOperationController {

    @Autowired
    private IJoyeaOperationService joyeaManufactureTaskService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listByManufactureOrderId(@RequestBody TransOperationRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<JoyeaOperation> operations = joyeaManufactureTaskService.findByManufacturerOrderId(request.getManufactureId());

        response.setList(operations);
        response.setCode("0");
        return response;
    }
}
