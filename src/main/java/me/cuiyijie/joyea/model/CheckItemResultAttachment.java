package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/15 23:17
 */
@Data
@TableName("SYX_ZLGL_LENOVOFILE")
public class CheckItemResultAttachment {

    @TableId(value = "FID", type = IdType.ASSIGN_UUID)
    private String fid;

    @TableField("CHECKRESULTID")
    private String checkResultId;

    @TableField("FILETYPE")
    private String fileType;

    @TableField("LENOVOID")
    private String lenovoId;

    @TableField("UPDATETIME")
    private Date updateTime;

    @TableField("FILENAME")
    private String fileName;

    @TableField("MIMETYPE")
    private String mimeType;

}
