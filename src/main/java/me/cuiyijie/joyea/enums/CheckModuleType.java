package me.cuiyijie.joyea.enums;

import me.cuiyijie.joyea.enums.base.BaseEnum;

public enum CheckModuleType implements BaseEnum {
    TYBZYXZ("1", "通用标准验证项"),
    CJFSWT("2", "曾经发生问题"),
    FBURS("3", "非标URS"),
    CPZTZKSC("4", "产品装调质控手册"),
    HTZLZB("5", "合同质量指标"),
    ;

    private String key;
    private String value;

    CheckModuleType(String key, String value) {
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
