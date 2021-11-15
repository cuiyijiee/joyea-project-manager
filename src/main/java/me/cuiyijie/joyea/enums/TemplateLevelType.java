package me.cuiyijie.joyea.enums;

import me.cuiyijie.joyea.enums.base.BaseEnum;

public enum TemplateLevelType implements BaseEnum {

    DIR("1", "文件夹"),  //文件夹
    OPERATION("2", "工序"),  //工序
    CHECKITEM("3", "点检项");   //点检项

    private String key;
    private String value;

    TemplateLevelType(String key, String value) {
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
