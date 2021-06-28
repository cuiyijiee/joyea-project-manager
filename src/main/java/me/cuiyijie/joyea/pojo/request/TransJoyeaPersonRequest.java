package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

@Data
public class TransJoyeaPersonRequest {

    private Integer pageNumber;
    private Integer pageSize;

    private String name;

}
