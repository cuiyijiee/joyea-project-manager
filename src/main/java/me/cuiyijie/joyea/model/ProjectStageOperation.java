package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectStageOperation {

    private Integer id;
    private Integer stageRelId;
    private Integer parentId;
    private String parentName;
    private Integer operationId;
    private String operationName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    private List<CheckItem> checkItems;
}
