package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.ProjectStage;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/30 14:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectStageVo extends ProjectStage {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
