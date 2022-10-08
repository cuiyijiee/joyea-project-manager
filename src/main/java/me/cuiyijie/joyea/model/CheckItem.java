package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@TableName("VW_SYX_ZLGL_DJX")
@ApiModel(value = "点检项信息")
public class CheckItem {

    @TableField("FID")
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
}
