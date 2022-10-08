package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/8 21:02
 */
@Data
@TableName("T_BD_PERSON")
public class Person {

    @TableId(value = "FID", type = IdType.ASSIGN_UUID)
    private String fid;

    @TableField("FNAME_L2")
    private String fName;

}
