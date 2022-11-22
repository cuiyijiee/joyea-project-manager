package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: yjcui3
 * @Date: 2022/9/28 11:23
 */
@Data
@TableName("VW_SYX_ZLGL_XMLB")
@ApiModel(value = "项目信息")
public class Project {

    @ApiModelProperty(value = "项目ID")
    @TableId(type = IdType.ASSIGN_UUID, value = "FID")
    private String fid;

    @ApiModelProperty(value = "项目编号")
    @TableField("FNUMBER")
    private String fNumber;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

    @ApiModelProperty(value = "项目负责人")
    @TableField("XMFZR")
    private String xmFzr;

    @ApiModelProperty(value = "项目所属部门")
    @TableField("DEPART")
    private String depart;

//    @ApiModelProperty(value = "客户专员")
//    @TableField("KHZY")
//    private String proejctKhzy;

    @ApiModelProperty(value = "装调部门")
    @TableField("ZDDEPT")
    private String zdDept;

    @ApiModelProperty(value = "装调负责人")
    @TableField("ZDDEPTMANS")
    private String zdDeptMans;

    @ApiModelProperty(value = "是否被收藏")
    @TableField(exist = false)
    private boolean isCollect;

}
