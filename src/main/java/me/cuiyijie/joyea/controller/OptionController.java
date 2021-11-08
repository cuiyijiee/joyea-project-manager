package me.cuiyijie.joyea.controller;


import io.swagger.annotations.Api;
import me.cuiyijie.joyea.enums.*;
import me.cuiyijie.joyea.enums.base.BaseEnum;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("option")
@Api(tags = "各个选项框的选项")
public class OptionController {

    @PostMapping("checkModule")
    public TransBaseResponse checkModuleOption() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(genKv(CheckModuleType.values()));
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

    @PostMapping("checkCategory")
    public TransBaseResponse checkCategoryOption() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(genKv(CheckCategoryType.values()));
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

    @PostMapping("checkStage")
    public TransBaseResponse checkStageOption() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(genKv(CheckStageType.values()));
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

    @PostMapping("checkVerify")
    public TransBaseResponse checkVerifyOption() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(genKv(CheckVerifyType.values()));
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

    @PostMapping("templateLevel")
    public TransBaseResponse templateLevelOption() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(genKv(TemplateLevelType.values()));
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

    @PostMapping("checkItemTag")
    public TransBaseResponse checkItemTagOption() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(genKv(CheckItemTagType.values()));
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }


    private List<Map<String, String>> genKv(BaseEnum[] baseEnums) {
        List<Map<String, String>> result = new ArrayList<>();
        for (BaseEnum baseEnum : baseEnums) {
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("id", baseEnum.getKey());
            resultMap.put("value", baseEnum.getValue());
            result.add(resultMap);
        }
        return result;
    }

}
