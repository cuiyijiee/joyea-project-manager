package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("T_PM_USER")
public class User {

    @TableId(value = "FID", type = IdType.ASSIGN_UUID)
    private String fid;

    @TableField("FPERSONID")
    private String fPersonId;

}
