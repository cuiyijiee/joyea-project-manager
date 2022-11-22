package me.cuiyijie.joyea.pojo.request;

import lombok.Data;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 14:03
 */
@Data
public class TransProductRequest {

    private Integer pageSize = 10;
    private Integer pageNum = 1;

    private String fid;
    private String productName;

    private Integer status;

    private String orderId;

}
