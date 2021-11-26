package me.cuiyijie.joyea.pojo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/19 15:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TransPageCheckItemTagRequest extends TransCheckItemTagRequest{

    private Integer pageSize = 10;
    private Integer pageNum = 1;

}
