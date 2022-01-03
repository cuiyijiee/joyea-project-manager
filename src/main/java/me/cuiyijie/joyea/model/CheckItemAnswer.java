package me.cuiyijie.joyea.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/18 22:35
 */
@Data
public class CheckItemAnswer {

    private Integer id;
    private Integer stageRelId;
    private Integer checkItemId;

    private Boolean firstCheckVerifyResult;
    private String firstCheckVerifyTextValue;
    private String firstCheckVerifyNumberValue;
    private String firstCheckVerifyImageId;
    private String firstCheckVerifyVideoId;
    private String firstCheckVerifyFileId;
    private String firstCheckRemark;
    private String firstCheckOperator;

    private Boolean secondCheckVerifyResult;
    private String secondCheckVerifyTextValue;
    private String secondCheckVerifyNumberValue;
    private String secondCheckVerifyImageId;
    private String secondCheckVerifyVideoId;
    private String secondCheckVerifyFileId;
    private String secondCheckRemark;
    private String secondCheckOperator;

    private Boolean thirdCheckVerifyResult;
    private String thirdCheckVerifyTextValue;
    private String thirdCheckVerifyNumberValue;
    private String thirdCheckVerifyImageId;
    private String thirdCheckVerifyVideoId;
    private String thirdCheckVerifyFileId;
    private String thirdCheckRemark;
    private String thirdCheckOperator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
