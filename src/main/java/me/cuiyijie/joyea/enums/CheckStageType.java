package me.cuiyijie.joyea.enums;

import me.cuiyijie.joyea.enums.base.BaseEnum;

public enum CheckStageType implements BaseEnum {

    XMXQ("1", "项目需求阶段"),  //选择是否
    SRSCPS("2", "输入输出评审阶段"),  //选择是否
    WXCG("3", "外协采购阶段"),  //选择是否
    ZP("4", "装配阶段"),  //选择是否
    GNYZ("5", "功能验证阶段"),  //选择是否
    HTZBYZ("6", "合同指标验证阶段"),  //选择是否
    JFATYZ("7", "JFAT验证阶段"),  //选择是否
    FATYZ("8", "FAT验证"),  //选择是否
    SATYZ("9", "SAT验证"),  //选择是否
    DSFYZ("10", "第三方验证"),  //选择是否
    DJURS("11", "大件URS"),  //选择是否
    DJFAT("12", "大件FAT"),  //选择是否
    JSATYZ("13", "JSAT验证");   //表格


    private String key;
    private String value;

    CheckStageType(String key, String value) {
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
