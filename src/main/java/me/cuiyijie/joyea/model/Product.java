package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 10:13
 */
@Data
public class Product {

    private Integer id;
    private String projectId;
    private String productNumber;
    private String productName;
    /**
     * 生产单号
     */
    private String productionNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
