package me.cuiyijie.joyea.pojo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.Template;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransTemplateRequest extends Template {

    private Integer pageSize;
    private Integer pageNum;

    private List<Integer> ids;

}
