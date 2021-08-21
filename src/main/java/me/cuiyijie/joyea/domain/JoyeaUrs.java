package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
public class JoyeaUrs {

    //id
    private String ursId;

    //编号
    private String ursNumber;

    //商机编号
    private String saleProjectId;

    //分录序号
    private String cfSeq;

    //模块分类
    private String cfType;

    //我司标准
    private String ourStandard;

    //客户标准
    private String customStandard;

    //是否可响应
    private String isSatisfy;

    //我司回复
    private String conclusion;

    //备注说明
    private String remark;

    //设置
    private JoyeaUrsSettings settings;
}
