package me.cuiyijie.joyea.pojo;

import lombok.Data;

@Data
public class TransProjectRequest {

    private Integer pageSize = 10;
    private Integer pageNumber = 1;

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



}
