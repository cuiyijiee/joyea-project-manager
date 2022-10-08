package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.CheckItemResult;

/**
 * @Author: yjcui3
 * @Date: 2022/10/8 16:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckItemResultVo extends CheckItemResult {

    private Integer pageSize = 10;
    private Integer pageNum = 1;


}
