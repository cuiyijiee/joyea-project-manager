package me.cuiyijie.joyea.model;

import lombok.Data;

@Data
public class ProjectStageOperation {

    private Integer id;
    private Integer stageRelId;
    private Integer parentId;
    private String parentName;
    private Integer operationId;
    private String operationName;

//    private List<CheckItem> checkItems;
}
