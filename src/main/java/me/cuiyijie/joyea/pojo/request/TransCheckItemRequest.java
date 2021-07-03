package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

@Data
public class TransCheckItemRequest {


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
