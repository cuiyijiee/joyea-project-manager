package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
public class CheckItemFile {

    private Integer id;
    private String checkItemId;
    private Integer fileId;
    //附件类型，1-自检，2-互检，3-专检
    private Integer fileType;

}
