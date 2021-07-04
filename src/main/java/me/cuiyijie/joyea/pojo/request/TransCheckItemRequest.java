package me.cuiyijie.joyea.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransCheckItemRequest {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

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
    private Integer specialCheckType;
    @ApiModelProperty("专检人选项检验结果，默认为false")
    private boolean specialCheckResult;

    //互检人
    private String mutualCheckPersonId;
    private String mutualCheckPersonName;
    private Integer mutualCheckType;
    private boolean mutualCheckResult;

    //自检人
    private String selfCheckPersonId;
    private String selfCheckPersonName;
    private Integer selfCheckType;
    private boolean selfCheckResult;

}
