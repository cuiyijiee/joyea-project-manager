package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

import java.util.List;

@Data
public class TransCheckItemFormRequest {


    private String checkItem;
    private Integer rowIndex;
    private Integer dataType;

    private List<FormData> data;


    @Data
    public static class FormData {
        private Integer id;
        private String value;
    }
}
