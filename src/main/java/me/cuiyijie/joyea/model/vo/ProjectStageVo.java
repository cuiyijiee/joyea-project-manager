package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import me.cuiyijie.joyea.model.ProjectStage;

/**
 * @Author: yjcui3
 * @Date: 2021/11/30 14:31
 */
@Data
public class ProjectStageVo extends ProjectStage {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
