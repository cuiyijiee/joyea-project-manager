package me.cuiyijie.joyea.enums;

import me.cuiyijie.joyea.enums.base.BaseEnum;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/26 14:10
 */
public enum CheckItemPropertyType implements BaseEnum {


    MODULE("1", "验证模块"),
    CATEGORY("2", "验证类别"),
    STAGE("3", "验证阶段");

    private String key;
    private String value;

    CheckItemPropertyType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
