package me.cuiyijie.joyea.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.joyea.model.SysFileUpload;
import me.cuiyijie.joyea.model.CheckItemAnswer;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/24 15:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckItemAnswerVo extends CheckItemAnswer {


    private SysFileUpload firstCheckVerifyImage;
    private SysFileUpload firstCheckVerifyVideo;
    private SysFileUpload firstCheckVerifyFile;

    private SysFileUpload secondCheckVerifyImage;
    private SysFileUpload secondCheckVerifyVideo;
    private SysFileUpload secondCheckVerifyFile;

    private SysFileUpload thirdCheckVerifyImage;
    private SysFileUpload thirdCheckVerifyVideo;
    private SysFileUpload thirdCheckVerifyFile;

}
