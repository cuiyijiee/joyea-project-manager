package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@TableName("VW_SYX_ZLGL_DJX")
@ApiModel(value = "点检项信息")
public class CheckItem {

    @TableId("FID")
    private String fid;

    @TableField("CFTASKID")
    private String taskId;

    @TableField("CFCHECKMODELID")
    private String checkModeId;

    @TableField("CFTYPEID")
    private String typeId;

    @TableField("CFSEQ")
    private Long seq;

    @TableField("CFKEYITEM")
    private Long keyItem;

    @TableField("CFCHECKSTANDARD")
    private String checkStandard;

    @TableField("CFNEEDTEXT")
    private Boolean needText;

    @TableField("CFNEEDATTACHMENT")
    private Boolean needAttachment;

    @TableField("CFNEEDPICTURE")
    private Boolean needPicture;

    @TableField("CFNEEDVIDEO")
    private Boolean needVideo;

    @TableField("CFTYPENAME")
    private String typeName;

    @TableField("ZYFFID")
    private String checkMethodId;

    @TableField("ZYFF")
    private String checkMethod;

    @TableField(exist = false)
    private List<CheckItemAttachment> attachmentList;

    @ApiModelProperty("是否合格")
    @TableField(exist = false)
    private boolean qualified;

    @ApiModelProperty("是否完成")
    @TableField(exist = false)
    private boolean finished;

    @ApiModelProperty("点检类型")
    @TableField(exist = false)
    private Integer cfCheckType = 1;
}
