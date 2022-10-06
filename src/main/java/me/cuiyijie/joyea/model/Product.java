package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 10:13
 */
@Data
@ApiModel(value = "产品信息")
@TableName("VW_SYX_ZLGL_CP")
public class Product {

    @ApiModelProperty(value = "项目ID")
    @TableField("XMID")
    private String projectId;

    @ApiModelProperty(value = "生产订单ID")
    @TableId(value = "ORDERID")
    private String orderId;

    @ApiModelProperty(value = "生产订单编号")
    @TableField("ORDERNUMBER")
    private String orderNumber;

    @ApiModelProperty(value = "产品编号")
    @TableField("PRODUCTNUMBER")
    private String productNumber;

    @ApiModelProperty(value = "产品名称")
    @TableField("PRODUCTNAME")
    private String productName;

}
