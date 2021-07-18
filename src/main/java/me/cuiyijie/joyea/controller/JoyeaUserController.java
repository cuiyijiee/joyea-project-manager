package me.cuiyijie.joyea.controller;

import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import me.cuiyijie.joyea.domain.JoyeaPerson;
import me.cuiyijie.joyea.pojo.NextPlusTenant;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransJoyeaPersonRequest;
import me.cuiyijie.joyea.service.IJoyeaPersonService;
import me.cuiyijie.joyea.service.INextPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("joyeaUser")
@Api(tags = "EAS用户模块")
public class JoyeaUserController {

    @Autowired
    private IJoyeaPersonService joyeaPersonService;

    @Autowired
    private INextPlusService nextPlusService;

    @Autowired
    RestTemplate restTemplate;


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

    @RequestMapping(value = "nextplus/list", method = RequestMethod.POST)
    public TransBaseResponse listNextPlusPerson(@RequestBody TransJoyeaPersonRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        NextPlusTenant nextPlusTenant = nextPlusService.getTenantInfo();
        if (StringUtil.isNotEmpty(request.getName()) && nextPlusTenant != null) {
            response.setObj(nextPlusTenant.getMembers().stream().filter(item -> item.getName().contains(request.getName())).collect(Collectors.toList()));
        } else {
            response.setObj(nextPlusTenant.getMembers());
        }
        response.setCode("0");
        return response;
    }

    @RequestMapping(value = "nextplus/notification", method = RequestMethod.POST)
    public TransBaseResponse testNotification(@RequestBody TransJoyeaPersonRequest request) {
        TransBaseResponse response = new TransBaseResponse();

        List<String> ids = new ArrayList<>();
        ids.add("8a9c8ff35deb01ad015dee4c08920019");
        nextPlusService.sendNotify(ids, "测试推送");
        return response;
    }


}
