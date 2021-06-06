package me.cuiyijie.joyea.pojo;

import lombok.Data;

@Data
public class TransBaseProjectRequest {

    private Integer pageNum = 1;
    private Integer pageSize = 10;

    //项目编号
    private String projectNumber;
    /**
     * 生产单号
     */
    private String manufactureNumber;

    /**
     * 产品编号
     */
    private String materialNumber;

    //主任务开始时间
    private String taskStartTime;

    //主任务结束时间
    private String taskEndTime;

    //任务类型
    private String taskCategory;

}
