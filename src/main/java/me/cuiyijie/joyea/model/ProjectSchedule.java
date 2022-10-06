package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/5 20:54
 */

@Data
@TableName("VW_SYX_ZLGL_XMWCL")
@ApiModel(value = "项目完成率")
public class ProjectSchedule {

    @ApiModelProperty(value = "项目ID")
    @TableField("FID")
    private String fid;

    @ApiModelProperty(value = "项目编号")
    @TableField("FNUMBER")
    private String fNumber;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty(value = "自检总点检项")
    @TableField("SELF")
    private Long self;

    @ApiModelProperty(value = "自检已点数")
    @TableField("SELFFINISH")
    private Long selfFinish;

    @ApiModelProperty(value = "自检合格")
    @TableField("SELFGOOD")
    private Long selfGood;

    @ApiModelProperty(value = "互检总点检项")
    @TableField("EACH")
    private Long each;

    @ApiModelProperty(value = "互检已点数")
    @TableField("EACHFINISH")
    private Long eachFinish;

    @ApiModelProperty(value = "互检合格")
    @TableField("EACHGOOD")
    private Long eachGood;
}
