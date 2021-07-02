package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import me.cuiyijie.joyea.domain.JoyeaPerson;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransJoyeaPersonRequest;
import me.cuiyijie.joyea.service.IJoyeaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("joyeaUser")
@Api(tags = "EAS用户模块")
public class JoyeaUserController {

    @Autowired
    private IJoyeaPersonService joyeaPersonService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse listJoyeaPerson(@RequestBody TransJoyeaPersonRequest request) {

        JoyeaPerson joyeaPerson = new JoyeaPerson();
        joyeaPerson.setName(request.getName());

        List<JoyeaPerson> personList = joyeaPersonService.list(joyeaPerson);

        TransBaseResponse response = new TransBaseResponse();
        response.setCode("0");
        response.setList(personList);

        return response;
    }

}
