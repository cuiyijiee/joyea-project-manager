package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("SYX_ZLGL_SEARCH_HISTORY")
public class SearchHistory {

    @TableId(value = "FID",type = IdType.ASSIGN_ID)
    private String fid;

    private String easUserId;

    private String searchType;

    private String searchKey;

    private Date updateTime;

}
