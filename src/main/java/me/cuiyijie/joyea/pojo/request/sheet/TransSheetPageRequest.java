package me.cuiyijie.joyea.pojo.request.sheet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransSheetPageRequest extends TransSheetRequest {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
