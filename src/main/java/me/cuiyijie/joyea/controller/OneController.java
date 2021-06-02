package me.cuiyijie.joyea.controller;

import me.cuiyijie.joyea.domain.Inventory;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OneController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/")
    @ResponseBody
    public TransBaseResponse index() {
        TransBaseResponse response = new TransBaseResponse();
        List result = inventoryService.findAll();
        response.setCode("0");
        response.setList(result);
        return response;
    }
}
