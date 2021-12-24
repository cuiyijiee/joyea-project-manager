package me.cuiyijie.joyea.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.SheetData;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/12/24 22:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TransSheetDataRequest extends SheetData {

    private Integer sheetId;
    private Integer stageRelId;
    private List<SheetData> sheetData;


}
