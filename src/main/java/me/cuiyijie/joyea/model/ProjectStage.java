package me.cuiyijie.joyea.model;

import lombok.Data;
import me.cuiyijie.joyea.enums.CheckStageType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/11/30 11:07
 */
@Data
public class ProjectStage {

    private Integer id;
    private Integer projectId;
    private CheckStageType checkStageType;
    private Boolean containsProject;
    private List<StageProduct> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean enabled;

    public String getCheckStageTypeValue(){
        return checkStageType != null ? checkStageType.getValue() : null;
    }

}
