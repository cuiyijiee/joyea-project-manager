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
    private String firstCheckRemark;

    private Boolean secondCheckVerifyResult;
    private String secondCheckRemark;

    private Boolean thirdCheckVerifyResult;
    private String thirdCheckRemark;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
