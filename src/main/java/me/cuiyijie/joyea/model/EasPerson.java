package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/12 21:14
 */
@Data
@TableName("T_PM_USER")
@ApiModel(value = "EAS人员信息")
public class EasPerson {

    @TableId("FID")
    private String fid;

    @TableField("FNAME_L2")
    private String fNameL2;

    @TableField("FPERSONID")
    private String fPersonId;

}
