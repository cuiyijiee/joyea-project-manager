package me.cuiyijie.joyea.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.models.auth.In;
import lombok.Data;
import me.cuiyijie.joyea.enums.CheckCategoryType;
import me.cuiyijie.joyea.enums.CheckModuleType;
import me.cuiyijie.joyea.enums.CheckStageType;
import me.cuiyijie.joyea.enums.CheckVerifyType;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CheckItem {

    private Integer id;

    private CheckModuleType checkModuleType;   //验证模块
    private CheckCategoryType checkCategoryType; //验证类别
    private CheckStageType checkStageType; //验证阶段
    private String checkName;  //验证项目
    private Boolean checkNameVisible;  //验证项目是否可见
    private String checkStandard; //验证标准
    private Boolean checkStandardVisible; //验证标准是否可见
    private String checkMethod; //验证方法
    private Boolean checkMethodVisible; //验证方法是否可见
    private String checkTool; //验证工具
    private Boolean checkToolVisible; //验证工具是否可见
    private String checkStandardFrom; //验证标准来源
    private Boolean checkStandardFromVisible; //验证标准来源是否可见

    //产品标签/工序标签
    private List<CheckItemTag> tags;

    //自检人
    private CheckVerifyType firstCheckVerifyType;
    private Boolean firstCheckVerifyResult;
    private String firstCheckVerifySheetId;
    private String firstCheckPersonId;
    private String firstCheckRemark;

    //互检人
    private CheckVerifyType secondCheckVerifyType;
    private Boolean secondCheckVerifyResult;
    private String secondCheckVerifySheetId;
    private String secondCheckPersonId;
    private String secondCheckRemark;

    //第三方检验人
    private CheckVerifyType thirdCheckVerifyType;
    private Boolean thirdCheckVerifyResult;
    private String thirdCheckVerifySheetId;
    private String thirdCheckPersonId;
    private String thirdCheckRemark;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean enabled;

    public String getCheckModuleTypeValue(){
        return checkModuleType == null ? "" :checkModuleType.getValue();
    }

    public String getCheckCategoryTypeValue(){
        return checkCategoryType == null ? "" :checkCategoryType.getValue();
    }

    public String getCheckStageTypeValue(){
        return checkStageType == null ? "" :checkStageType.getValue();
    }

    public String getFirstCheckVerifyTypeValue(){
        return firstCheckVerifyType == null ? "" :firstCheckVerifyType.getValue();
    }

    public String getSecondCheckVerifyTypeValue(){
        return secondCheckVerifyType == null ? "" :secondCheckVerifyType.getValue();
    }

    public String getThirdCheckVerifyTypeValue(){
        return thirdCheckVerifyType == null ? "" :thirdCheckVerifyType.getValue();
    }

}
