package me.cuiyijie.joyea.pojo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.Template;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransOperationRequest extends Template {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
