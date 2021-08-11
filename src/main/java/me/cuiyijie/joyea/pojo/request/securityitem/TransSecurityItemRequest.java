package me.cuiyijie.joyea.pojo.request.securityitem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransSecurityItemRequest {

    private String projectNumber;
    private String checkStandard;
    private String checkTypeId;
    private String checkTypeName;

    //专检人
    private String specialCheckPersonId;
    private String specialCheckPersonName;
    @ApiModelProperty("专检人选项检验结果，默认为false")
    private boolean specialCheckResult;
    private String specialCheckRemark;

    //互检人
    private String mutualCheckPersonId;
    private String mutualCheckPersonName;
    private boolean mutualCheckResult;
    private String mutualCheckRemark;

    //自检人
    private String selfCheckPersonId;
    private String selfCheckPersonName;
    private boolean selfCheckResult;
    private String selfCheckRemark;

}
