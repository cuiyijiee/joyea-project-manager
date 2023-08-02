package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/9 22:40
 */
@Data
@TableName("VW_SYX_ZLGL_ZYFFATTACH")
public class CheckItemAttachment {

    @TableField(value = "FID")
    private String fid;

    @TableId("ATTACH_FID")
    private String attachFid;

    @TableField("ATTACH_NAME")
    private String attachName;

    @TableField("ATTACHE_TYPE")
    private String attacheType;

    @TableField("FSIZE")
    private String fSize;

    @TableField("FATTACHID")
    private String fAttachId;

    @TableField("FREMOTEPATH")
    private String fRemotePath;
}
