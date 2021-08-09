package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
public class SheetColumn {

    private Integer id;
    private Integer sheetId;
    private Integer columnIndex;
    private String columnName;
    private Integer columnType;
    private String columnValueStandard;
    private String columnValueMax;
    private String columnValueMin;
    private Boolean columnValueMaxInclude;
    private Boolean columnValueMinInclude;

}
