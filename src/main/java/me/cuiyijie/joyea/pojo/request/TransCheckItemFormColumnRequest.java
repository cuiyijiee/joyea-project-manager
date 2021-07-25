package me.cuiyijie.joyea.pojo.request;


import lombok.Data;

import java.util.List;

@Data
public class TransCheckItemFormColumnRequest {

    private String checkItemId;
    private Integer columnId;  //表格列的ID
    private Integer dataType;  //数据类型，指定是自检-1，互检-2，专检-3

    private List<InnerClass> data;

    @Data
    public static class InnerClass {

        private Integer rowIndex;
        private String value;

    }

}
