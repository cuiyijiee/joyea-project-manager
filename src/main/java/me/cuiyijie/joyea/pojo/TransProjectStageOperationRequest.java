package me.cuiyijie.joyea.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.ProjectStageOperation;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransProjectStageOperationRequest extends ProjectStageOperation {

    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
