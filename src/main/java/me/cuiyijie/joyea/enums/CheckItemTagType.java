package me.cuiyijie.joyea.enums;

import me.cuiyijie.joyea.enums.base.BaseEnum;

/**
 * @Author: yjcui3
 * @Date: 2021/11/8 10:33
 */
public enum CheckItemTagType implements BaseEnum {

    OPERATION("1", "工序标签"),
    PRODUCT("2", "产品标签");

    private String key;
    private String value;

    CheckItemTagType(String key, String value) {
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
