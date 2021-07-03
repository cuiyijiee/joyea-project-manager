package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
//仅一点检项接口
public class JoyeaCheckItem {


    private String id;
    private String seq;
    private String checkStandard;
    private String checkTypeId;
    private String checkTypeName;

    private String operationId;
    private String operationNo;

    //专检人
    private String mainCheckPersonId;
    private String mainCheckPersonName;

    //互检人
    private String minorCheckPersonId;
    private String minorCheckPersonName;

}
