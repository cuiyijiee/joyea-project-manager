package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.Template;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateVo extends Template {
    private Integer pid;
    private List<Integer> ids;
    private Integer childCount;

    private String parentName;
}
