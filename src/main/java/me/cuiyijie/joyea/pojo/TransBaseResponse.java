package me.cuiyijie.joyea.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TransBaseResponse {

    private String code = "0";
    private String msg;

    private Object obj;

    private List list;

}
