package me.cuiyijie.joyea.model;

import lombok.Data;
import me.cuiyijie.joyea.enums.CheckCategoryType;
import me.cuiyijie.joyea.enums.CheckModuleType;
import me.cuiyijie.joyea.enums.CheckStageType;
import me.cuiyijie.joyea.enums.CheckVerifyType;

import java.time.LocalDateTime;

@Data
public class CheckItem {

    private int id;

    private CheckModuleType checkModuleType;
    private CheckCategoryType checkCategoryType;
    private CheckStageType checkStageType;
    private String checkName;
    private String checkStandard;
    private String checkMethod;
    private String checkTool;
    private String checkStandardFrom;

    //自检人
    private CheckVerifyType firstCheckVerifyType;
    private String firstCheckVerifyResult;
    private String firstCheckVerifySheetId;
    private String firstCheckPersonId;

    //互检人
    private CheckVerifyType secondCheckVerifyType;
    private String secondCheckVerifyResult;
    private String secondCheckVerifySheetId;
    private String secondCheckPersonId;

    //第三方检验人
    private CheckVerifyType thirdCheckVerifyType;
    private String thirdCheckVerifyResult;
    private String thirdCheckVerifySheetId;
    private String thirdCheckPersonId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
