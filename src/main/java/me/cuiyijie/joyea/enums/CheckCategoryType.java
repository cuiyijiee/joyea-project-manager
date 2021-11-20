package me.cuiyijie.joyea.enums;

import me.cuiyijie.joyea.enums.base.BaseEnum;

public enum CheckCategoryType implements BaseEnum {

    ALL("0", "全部"),  //选择是否
    WHBY("1", "维护保养类"),  //选择是否
    QZAQ("2", "强制安全类"),  //选择是否
    DQBZ("3", "电气标准类"),  //选择是否
    WGWS("4", "外观卫生类"),  //选择是否
    CPGN("5", "产品功能类"),  //选择是否
    WJZL("6", "文件资料类"),  //选择是否
    ZDCPYZ("7", "终端产品验证");   //表格


    private String key;
    private String value;

    CheckCategoryType(String key, String value) {
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