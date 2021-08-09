package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

@Data
public class TransCheckItemDataRequest {

    private String checkItemId;
    private Integer rowIndex;
    private Integer columnId;
    private Integer dataType;  //数据类型，指定是自检-1，互检-2，专检-3

}
