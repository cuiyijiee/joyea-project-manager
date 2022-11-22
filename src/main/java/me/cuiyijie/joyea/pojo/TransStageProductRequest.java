package me.cuiyijie.joyea.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.StageProduct;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransStageProductRequest extends StageProduct {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
