package me.cuiyijie.joyea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.cuiyijie.joyea.enums.CheckItemPropertyType;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/26 14:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckItemProperty {

    private Integer id;
    private Integer checkItemId;

    private CheckItemPropertyType propertyType;
    private String propertyValue;

}
