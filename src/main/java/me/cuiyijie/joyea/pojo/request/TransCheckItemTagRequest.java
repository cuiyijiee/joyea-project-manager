package me.cuiyijie.joyea.pojo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.CheckItemTag;

/**
 * @Author: yjcui3
 * @Date: 2021/11/19 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TransCheckItemTagRequest extends CheckItemTag {

    private Integer checkItemId;
}
