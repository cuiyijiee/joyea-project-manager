package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.SheetColumn;
import me.cuiyijie.joyea.model.SheetData;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/24 22:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SheetColumnVo extends SheetColumn {

    private List<SheetData> dataList;

}
