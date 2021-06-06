package me.cuiyijie.joyea.domain;

import lombok.Data;

import java.util.Date;

@Data
public class JoyeaManufactureTask {

    //任务ID
    private String taskId;

    //工序名称
    private String operationName;

    //状态
    private String status;

    //操作ID
    private String operationNo;

    //订单编号
    private String manufactureOrder;

    //负责人ID
    private String workPersonId;

    //负责人姓名
    private String workPersonName;

    //开工时间
    private Date startTime;

    //完工时间
    private Date endTime;

}
