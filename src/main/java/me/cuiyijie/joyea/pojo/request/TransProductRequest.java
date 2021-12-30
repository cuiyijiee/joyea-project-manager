package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

/**
 * @Author: cuiyijie
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
    /**
     * 生产单号
     */
    private String productionNumber;

}
