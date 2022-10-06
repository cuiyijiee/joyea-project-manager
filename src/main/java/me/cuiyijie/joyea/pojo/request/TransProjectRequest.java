package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

@Data
public class TransProjectRequest {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

    private String fNumber;
    private String projectName;
}
