package me.cuiyijie.joyea.pojo.request.projecttemplate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransProjectTemplatePageRequest extends TransProjectTemplateRequest {

    private Integer pageSize = 10;
    private Integer pageNum = 1;
}
