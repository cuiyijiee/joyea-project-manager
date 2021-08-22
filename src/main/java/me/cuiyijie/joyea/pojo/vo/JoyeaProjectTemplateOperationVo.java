package me.cuiyijie.joyea.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.domain.JoyeaOperation;

@EqualsAndHashCode(callSuper = true)
@Data
public class JoyeaProjectTemplateOperationVo extends JoyeaOperation {

    private Integer id;

    private Integer templateId;

}
