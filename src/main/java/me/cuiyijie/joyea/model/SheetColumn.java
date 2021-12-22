package me.cuiyijie.joyea.model;

import lombok.Data;

@Data
public class SheetColumn {

    //Id
    private Integer id;
    //表格Id
    private Integer sheetId;
    //第几列
    private Integer columnIndex;
    //列名称
    private String columnName;
    //列的type，1.字符串，2.数字
    private Integer columnType;
    //如果type=1，则是否合格标准是以是否与该值相等
    private String columnValueStandard;
    //最大值
    private Integer columnValueMax;
    //最小值
    private Integer columnValueMin;
    //是否包括最大值
    private Boolean columnValueMaxInclude;
    //是否包括最小值
    private Boolean columnValueMinInclude;

}
