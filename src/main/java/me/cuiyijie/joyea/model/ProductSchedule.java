package me.cuiyijie.joyea.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("VW_SYX_ZLGL_CPWCL")
@ApiModel(value = "产品完成率")
public class ProductSchedule {

    @ApiModelProperty(value = "项目ID")
    @TableField("FID")
    private String projectId;

    @ApiModelProperty(value = "项目编号")
    @TableField("FNUMBER")
    private String projectNumber;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECTNAME")
    private String projectName;

//    @ApiModelProperty(value = "物料编号")
//    @TableField("WLNUMBER")
//    private String wlNumber;
//
//    @ApiModelProperty(value = "物料名称")
//    @TableField("WLNAME")
//    private String wlName;

//    @ApiModelProperty(value = "产品编号")
//    @TableField("PRODUCTNUMBER")
//    private String productNumber;
//
//    @ApiModelProperty(value = "产品名称")
//    @TableField("PRODUCTNAME")
//    private String productName;

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

    @ApiModelProperty(value = "生产订单号")
    @TableId(value = "ORDERID")
    private String orderId;
}
