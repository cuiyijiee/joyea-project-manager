package me.cuiyijie.joyea.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Project {


    private Integer id;
    private String projectName;
    private String projectNumber;

    /**
     * 项目经理
     */
    private String projectManager;

    /**
     * 项目专员
     */
    private String projectCommissioner;

    /**
     * 项目负责人
     */
    private String projectPrincipal;

    /**
     * 项目所属部门
     */
    private String department;

    private Integer stageCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

}
