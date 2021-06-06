package me.cuiyijie.joyea.controller;

import me.cuiyijie.joyea.domain.JoyeaManufactureTask;
import me.cuiyijie.joyea.pojo.TransBaseManufactureOperationRequest;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.JoyeaManufactureTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manufactureTask")
public class ManufactureTaskOperationController {

    @Autowired
    private JoyeaManufactureTaskService joyeaManufactureTaskService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listByManufactureOrderId(@RequestBody TransBaseManufactureOperationRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<JoyeaManufactureTask> operations = joyeaManufactureTaskService.findByManufacturerOrderId(request.getManufactureId());

        response.setList(operations);
        response.setCode("0");
        return response;
    }
}
