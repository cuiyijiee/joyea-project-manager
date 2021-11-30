package me.cuiyijie.joyea.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StageProduct extends Product {

    private Integer stageProductRelId;
    private Boolean isProject;

    private List<ProjectStageOperation> projectStageOperations;

}
