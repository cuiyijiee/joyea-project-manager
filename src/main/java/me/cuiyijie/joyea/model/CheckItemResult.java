package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import java.util.Date;
import java.util.List;

@Data
@TableName("CT_PRE_QUALITYCHECKRESULT")
@ApiModel(value = "点检项点检信息")
public class CheckItemResult {

    @TableField("FNUMBER")
    private String fNumber;

    @TableField(value = "FSIMPLENAME")
    private String fSimpleName;

    @TableId(value = "FID", type = IdType.INPUT)
    private String fId;

    @TableField("FCREATORID")
    private String fCreatorId;

    @TableField("FCREATETIME")
    private Date fCreateTime;

    @TableField("FLASTUPDATEUSERID")
    private String fLastUpdateUserId;

    @TableField("FLASTUPDATETIME")
    private Date fLastUpdateTime;

    @TableField("FCONTROLUNITID")
    private String fControlUnitId;

    @TableField("CFCHECKENTRYID")
    private String cfCheckEntryId;

    @TableField("CFCHECKTYPE")
    private String cfCheckType;

    @TableField(value = "CFCHECKRESULT")
    private String cfCheckResult;

    @TableField(value = "CFREMARK")
    private String cfRemark;

    @TableField("CFCHECKPERSONID")
    private String cfCheckPersonId;

    @TableField(exist = false)
    private String cfCheckPersonName;

    @TableField("CFCHECKDATE")
    private Date cfCheckDate;

    @TableField("CFCHECKRECORDS")
    private String cfCheckRecords;

    @TableField("FNAME_L1")
    private String fNameL1;

    @TableField("FNAME_L2")
    private String fNameL2;

    @TableField("FNAME_L3")
    private String fNameL3;

    @TableField("FDESCRIPTION_L1")
    private String fDescriptionL1;

    @TableField("FDESCRIPTION_L2")
    private String fDescriptionL2;

    @TableField("FDESCRIPTION_L3")
    private String fDescriptionL3;

    @TableField("CFFIELDTYPE")
    private String cfFieldType;

    @TableField(exist = false)
    private List<CheckItemResultAttachment> attachmentList;
}
