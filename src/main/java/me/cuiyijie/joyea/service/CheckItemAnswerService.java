package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.CheckItemAnswerDao;
import me.cuiyijie.joyea.dao.SysFileUploadDao;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.SysFileUpload;
import me.cuiyijie.joyea.model.CheckItemAnswer;
import me.cuiyijie.joyea.model.vo.CheckItemAnswerVo;
import me.cuiyijie.joyea.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/18 23:28
 */
@Service
public class CheckItemAnswerService {

    @Autowired
    private CheckItemAnswerDao checkItemAnswerDao;

    @Autowired
    private SysFileUploadDao sysFileUploadDao;

    public Integer insert(CheckItemAnswer checkItemAnswer) {
        return checkItemAnswerDao.insert(checkItemAnswer);
    }

    public Integer updateResult(CheckItemAnswer checkItemAnswer) {
        CheckItemAnswer existAnswer = checkItemAnswerDao.select(checkItemAnswer);
        Integer result = 0;
        if (existAnswer == null) {
            result = checkItemAnswerDao.insert(checkItemAnswer);
        } else {
            result = checkItemAnswerDao.update(checkItemAnswer);
        }
        return result;
    }

    public CheckItemAnswerVo select(CheckItemAnswer checkItemAnswer) {
        CheckItemAnswer answer = checkItemAnswerDao.select(checkItemAnswer);

        CheckItemAnswerVo answerVo = new CheckItemAnswerVo();
        if(answer != null ) {
            BeanUtils.copyProperties(answer, answerVo);

            if (StringUtils.isNotBlank(answerVo.getFirstCheckVerifyImageId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getFirstCheckVerifyImageId());
                answerVo.setFirstCheckVerifyImage(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getFirstCheckVerifyVideoId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getFirstCheckVerifyVideoId());
                answerVo.setFirstCheckVerifyVideo(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getFirstCheckVerifyFileId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getFirstCheckVerifyFileId());
                answerVo.setFirstCheckVerifyFile(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getSecondCheckVerifyImageId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getSecondCheckVerifyImageId());
                answerVo.setSecondCheckVerifyImage(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getSecondCheckVerifyVideoId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getSecondCheckVerifyVideoId());
                answerVo.setSecondCheckVerifyVideo(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getSecondCheckVerifyFileId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getSecondCheckVerifyFileId());
                answerVo.setSecondCheckVerifyFile(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getThirdCheckVerifyImageId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getThirdCheckVerifyImageId());
                answerVo.setThirdCheckVerifyImage(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getThirdCheckVerifyVideoId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getThirdCheckVerifyVideoId());
                answerVo.setThirdCheckVerifyVideo(sysFileUpload);
            }
            if (StringUtils.isNotBlank(answerVo.getThirdCheckVerifyFileId())) {
                SysFileUpload sysFileUpload = sysFileUploadDao.selectById(answerVo.getThirdCheckVerifyFileId());
                answerVo.setThirdCheckVerifyFile(sysFileUpload);
            }
        }
        return answerVo;
    }

    public CheckItemAnswer selectById(CheckItemAnswer checkItemAnswer) {
        return checkItemAnswerDao.selectById(checkItemAnswer.getId());
    }

    public void updateStatus(Integer stageRelId, CheckItem checkItem) {
    }
}
