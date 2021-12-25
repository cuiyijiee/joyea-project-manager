package me.cuiyijie.joyea.pojo;


import lombok.Data;
import me.cuiyijie.joyea.model.SheetColumn;

import java.util.List;

@Data
public class TransSheetColumnRequest {

    private Integer sheetId;
    private List<SheetColumn> sheetColumn;

}
