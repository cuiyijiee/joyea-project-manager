package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: yjcui3
 * @Date: 2022/9/28 11:23
 */
@Data
@TableName("VW_SYX_ZLGL_XMLB")
public class NewProject {

    @TableField("FID")
    private String fid;

    @TableField("FNUMBER")
    private String fNumber;

    @TableField("PROJECTNAME")
    private String projectName;

    @TableField("XMFZR")
    private String projectFzr;

    @TableField("DEPART")
    private String projectDepart;

    @TableField("KHZY")
    private String proejctKhzy;

}
