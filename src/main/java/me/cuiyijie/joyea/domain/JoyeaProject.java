package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
public class JoyeaProject {

    /**
     * 生产单号
     */
    private String fNumber;

    /**
     * 销售编号
     */
    private String fRelatingOrderNum;

    /**
     * 销售序号
     */
    private String fRelatingEntrySeq;

    private String fMaterialID;

    private String fNameL2;

    /**
     * 名称
     */
    private String fNumber2;

    private String cFProjectName;

    private String fNumber3;

    private String cFTaskName;

    //是否含有非标URS
    private Boolean hasUrs;
}
