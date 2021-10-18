package me.cuiyijie.joyea.pojo;

import lombok.Data;

/**
 * @Author: yjcui3
 * @Date: 2021/10/18 14:03
 */
@Data
public class TransProductRequest {

    private Integer pageSize = 10;
    private Integer pageNumber = 1;

    private Integer id;

    private String projectId;
    private String productNumber;
    private String productName;

}
