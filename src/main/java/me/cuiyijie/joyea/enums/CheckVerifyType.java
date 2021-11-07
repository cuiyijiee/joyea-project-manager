package me.cuiyijie.joyea.enums;


import me.cuiyijie.joyea.enums.base.BaseEnum;

public enum CheckVerifyType implements BaseEnum {

    SWITCH("1", "选项"),  //选择是否
    SHEET("2", "表格");   //表格


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