package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

import java.util.List;

@Data
public class TransBaseResponse {

    private String code = "0";
    private String msg;

    private Object obj;

    private List list;

    public static TransBaseResponse failed(String errorMsg) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setMsg(errorMsg);
        transBaseResponse.setCode("-1");
        return transBaseResponse;
    }

    public static TransBaseResponse success(Object obj) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(obj);
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

    public static TransBaseResponse success(List list) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setList(list);
        transBaseResponse.setCode("0");
        return transBaseResponse;
    }

}
