package me.cuiyijie.joyea.pojo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.Template;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransTemplateRequest extends Template {

    private Integer pageSize;
    private Integer pageNum;

}
