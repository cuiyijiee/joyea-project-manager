package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/16 23:27
 */
@Data
@TableName("SYX_ZLGL_FAV")
public class ProjectCollect {

    @TableId(value = "FID", type = IdType.ASSIGN_UUID)
    private String fid;

    @TableField("PROJECTID")
    private String projectId;

    @TableField("EASUSERID")
    private String easUserId;

    @TableField("UPDATETIME")
    private Date updateTime;

}
