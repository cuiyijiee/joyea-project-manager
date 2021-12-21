package me.cuiyijie.joyea.enums;


import me.cuiyijie.joyea.enums.base.BaseEnum;

public enum CheckVerifyType implements BaseEnum {

    SHEET("1", "表格"),
    TEXT("2", "文字"),
    NUMBER("3", "数值"),
    IMAGE("4", "图片"),
    VIDEO("5", "视频"),
    FILE("6", "附件");

    private String key;
    private String value;

    CheckVerifyType(String key, String value) {
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