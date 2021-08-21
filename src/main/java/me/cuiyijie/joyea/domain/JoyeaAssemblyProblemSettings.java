package me.cuiyijie.joyea.domain;


import lombok.Data;

@Data
public class JoyeaAssemblyProblemSettings {

    private String problemId;

    private boolean selfCheckResult;
    private String selfCheckRemark;
    private boolean mutualCheckResult;
    private String mutualCheckRemark;
    private String mutualCheckPersonId;
    private String mutualCheckPersonName;
    private boolean specialCheckResult;
    private String specialCheckRemark;

}
