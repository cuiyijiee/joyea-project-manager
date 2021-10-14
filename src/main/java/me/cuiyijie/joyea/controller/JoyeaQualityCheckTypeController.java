//package me.cuiyijie.joyea.controller;
//
//import io.swagger.annotations.Api;
//import me.cuiyijie.joyea.domain.JoyeaQualityCheckType;
//import me.cuiyijie.joyea.pojo.TransBaseResponse;
//import me.cuiyijie.joyea.service.IJoyeaQualityCheckTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("qualityCheckType")
//@Api(tags = "EAS点检类型模块")
//public class JoyeaQualityCheckTypeController {
//
//    @Autowired
//    private IJoyeaQualityCheckTypeService joyeaQualityCheckTypeService;
//
//    @RequestMapping(value = "list", method = RequestMethod.POST)
//    public TransBaseResponse listAll() {
//
//        TransBaseResponse transBaseResponse = new TransBaseResponse();
//
//        List<JoyeaQualityCheckType> list = joyeaQualityCheckTypeService.listAll();
//
//        transBaseResponse.setList(list);
//        transBaseResponse.setCode("0");
//
//        return transBaseResponse;
//    }
//
//}
