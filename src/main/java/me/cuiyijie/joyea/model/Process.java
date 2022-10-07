package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("VW_SYX_ZLGL_GX")
@ApiModel(value = "工序信息")
public class Process {

    @ApiModelProperty(value = "项目ID")
    @TableField("FID")
    private String fId;

    @ApiModelProperty(value = "生产订单ID")
    @TableField("ORDERID")
    private String orderId;

    @ApiModelProperty(value = "任务ID")
    @TableField("TASKID")
    private String taskId;

    @ApiModelProperty(value = "工序编号")
    @TableField("GXNO")
    private String processNumber;

    @ApiModelProperty(value = "工序名称")
    @TableField("GXNAME")
    private String processName;

    @ApiModelProperty(value = "工序排序")
    @TableField("CFSEQ")
    private Long cfSeq;

}
