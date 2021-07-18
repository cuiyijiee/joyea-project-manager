package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
public class CheckItemFormData {


    private Integer id;  //数据ID
    private String checkItemId; //点检项Id
    private Integer seq; //列所在index（第几列）
    private String columnName; //列名称
    private Integer columnId; //列ID
    private Integer rowIndex; //行数index
    private String columnValue; //单元格数据
    private Integer dataType; //表格类型：1-自检，2-互检，3-专检

}
