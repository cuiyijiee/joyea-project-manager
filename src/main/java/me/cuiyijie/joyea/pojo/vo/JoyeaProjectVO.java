package me.cuiyijie.joyea.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class JoyeaProjectVO {

    //项目编号
    private String projectNumber;

    //项目名称
    private String projectName;

    //销售订单编号
    private String saleOrderNumber;

    //销售订单序号
    private String saleOrderSeq;

    //生产单号ID
    private String manufactureId;

    //生产编号
    private String manufactureNumber;

    //产品编号
    private String materialNumber;

    //产品ID
    private String materialId;

    //产品名称
    private String materialName;

    //任务编号
    private String taskNumber;

    //任务名称
    private String taskName;

    //主任务开始时间
    private Date taskStartTime;

    //主任务结束时间
    private Date taskEndTime;

    //任务类型
    private String taskCategory;

}
