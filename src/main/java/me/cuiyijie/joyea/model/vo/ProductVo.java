package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.Product;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/30 16:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductVo extends Product {



    private Integer operationId;

}
