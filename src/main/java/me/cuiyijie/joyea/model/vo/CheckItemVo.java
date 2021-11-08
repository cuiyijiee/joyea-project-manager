package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/11/8 14:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckItemVo extends CheckItem {

    private List<CheckItemTag> checkItemTags;

}
