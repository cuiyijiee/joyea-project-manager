package me.cuiyijie.joyea.pojo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.Sheet;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/24 21:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TransSheetRequest extends Sheet {

    private Integer pageNum = 1;
    private Integer pageSize = 10;

}
