package me.cuiyijie.joyea.pojo;

import lombok.Data;

/**
 * @Author: yjcui3
 * @Date: 2021/10/18 14:03
 */
@Data
public class TransProductRequest {

    private Integer pageNumber;
    private Integer pageSize;

    private String projectId;
    private String productNumber;
    private String productName;

}
