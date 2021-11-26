package me.cuiyijie.joyea.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import me.cuiyijie.joyea.enums.CheckItemTagType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/8 10:33
 */
@Data
public class CheckItemTag implements Serializable {

    private static final long serialVersionUID = -7155610581355123677L;

    @TableId("id")
    private Integer id;
    private String tagName;
    private CheckItemTagType tagType;
    private String remark1;
    private String remark2;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public String getTagTypeValue(){
        return tagType == null ? null : tagType.getValue();
    }
}
