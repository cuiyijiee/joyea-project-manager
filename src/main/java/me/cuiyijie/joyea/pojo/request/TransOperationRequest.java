package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

@Data
public class TransOperationRequest {

    private Integer pageNum = 1;
    private Integer pageSize = 10;

    private String manufactureId;

}
