package me.cuiyijie.joyea.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//仅一点检项接口
public class JoyeaCheckItem {


    private String id;
    private String seq;
    private String checkStandard;
    private String checkTypeId;
    private String checkTypeName;

    private String operationId;
    private String operationNo;

    //专检人
    private String specialCheckPersonId;
    private String specialCheckPersonName;
    @ApiModelProperty("专检人检验类型:0-选项检验，1-表单检验")
    private Integer specialCheckType = 0;
    @ApiModelProperty("专检人选项检验结果，默认为false")
    private boolean specialCheckResult = false;

    //互检人
    private String mutualCheckPersonId;
    private String mutualCheckPersonName;
    private Integer mutualCheckType = 0;
    private boolean mutualCheckResult = false;

    //自检人
    private String selfCheckPersonId;
    private String selfCheckPersonName;
    private Integer selfCheckType = 0;
    private boolean selfCheckResult = false;

}
