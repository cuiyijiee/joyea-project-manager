package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@TableName("CT_PRE_QUALITYCHECKRESULT")
@ApiModel(value = "点检项点检信息")
public class CheckItemResult {

    @TableField("FNUMBER")
    private String fNumber;

    @TableField("FSIMPLENAME")
    private String simpleName;

    @TableField("FID")
    private String fId;

    @TableField("FCREATORID")
    private String creatorId;

    @TableField("FCREATETIME")
    private Date createTime;

    @TableField("FLASTUPDATEUSERID")
    private String lastUpdateUserId;

    @TableField("FLASTUPDATETIME")
    private Date lastUpdateTime;

    @TableField("FCONTROLUNITID")
    private String controlUnitId;

    @TableField("CFCHECKENTRYID")
    private String checkEntryId;

    @TableField("CFCHECKTYPE")
    private String checkType;

    @TableField("CFCHECKRESULT")
    private String checkResult;

    @TableField("CFREMARK")
    private String remark;

    @TableField("CFCHECKPERSONID")
    private String checkPersonId;

    @TableField("CFCHECKDATE")
    private Date checkDate;

    @TableField("CFCHECKRECORDS")
    private String checkRecords;

    @TableField("FNAME_L1")
    private String nameL1;

    @TableField("FNAME_L2")
    private String nameL2;

    @TableField("FNAME_L3")
    private String nameL3;

    @TableField("FDESCRIPTION_L1")
    private String descriptionL1;

    @TableField("FDESCRIPTION_L2")
    private String descriptionL2;

    @TableField("FDESCRIPTION_L3")
    private String descriptionL3;

    @TableField("CFFIELDTYPE")
    private String fieldType;
}
