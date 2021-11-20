package me.cuiyijie.joyea.model;

import lombok.Data;
import me.cuiyijie.joyea.enums.CheckItemTagType;

import java.time.LocalDateTime;

/**
 * @Author: yjcui3
 * @Date: 2021/11/8 10:33
 */
@Data
public class CheckItemTag {


    private int id;
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
