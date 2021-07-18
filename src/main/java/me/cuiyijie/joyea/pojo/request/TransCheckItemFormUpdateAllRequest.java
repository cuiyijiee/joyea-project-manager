package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

import java.util.List;

@Data
public class TransCheckItemFormUpdateAllRequest {

    private String checkItem;
    private Integer dataType;

    private List<TransCheckItemFormUpdateAllRequest.RowData> rowData;

    @Data
    public static class RowData {

        private Integer rowIndex;
        private List<TransCheckItemFormRequest.FormData> data;

    }
}
