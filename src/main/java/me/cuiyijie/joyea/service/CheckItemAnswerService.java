package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.dao.main.*;
import me.cuiyijie.joyea.model.*;
import me.cuiyijie.joyea.model.vo.CheckItemAnswerVo;
import me.cuiyijie.joyea.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/18 23:28
 */
@Service
public class CheckItemAnswerService {


    @Autowired
    private CheckItemDao checkItemDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CheckItemAnswerDao checkItemAnswerDao;

    @Autowired
    private SysFileUploadDao sysFileUploadDao;

    @Autowired
    private CheckItemRecordDao checkItemRecordDao;

    public Integer insert(CheckItemAnswer checkItemAnswer) {
        return checkItemAnswerDao.insert(checkItemAnswer);
    }

    public Integer updateResult(CheckItemAnswer checkItemAnswer, CurrentUserInfo currentUserInfo) {
        CheckItemAnswer existAnswer = checkItemAnswerDao.select(checkItemAnswer);
        Integer result = 0;
        if (existAnswer == null) {

            boolean firstChecked = isFirstCheckHasChecked(checkItemAnswer);
            boolean secondChecked = isSecondCheckHasChecked(checkItemAnswer);
            boolean thirdChecked = isThirdCheckHasChecked(checkItemAnswer);

            if (
                    (firstChecked ? 1 : 0) + (secondChecked ? 1 : 0) + (thirdChecked ? 1 : 0) > 1
            ) {
                throw new RuntimeException("不能同时点检多个点检类型!");
            }
            //新增操作
            if (isFirstCheckHasChecked(checkItemAnswer)) {
                checkItemAnswer.setFirstCheckOperator(currentUserInfo.getId());
            } else if (isSecondCheckHasChecked(checkItemAnswer)) {
                checkItemAnswer.setSecondCheckOperator(currentUserInfo.getId());
            } else if (isThirdCheckHasChecked(checkItemAnswer)) {
                checkItemAnswer.setThirdCheckOperator(currentUserInfo.getId());
            }
            addCheckRecord(existAnswer, checkItemAnswer,
                    firstChecked, secondChecked, thirdChecked,
                    currentUserInfo);
            result = checkItemAnswerDao.insert(checkItemAnswer);
        } else {

            boolean firstChecked = isFirstCheckHasChanged(existAnswer, checkItemAnswer);
            boolean secondChecked = isSecondCheckHasChanged(existAnswer, checkItemAnswer);
            boolean thirdChecked = isThirdCheckHasChanged(existAnswer, checkItemAnswer);

            if (
                    (firstChecked ? 1 : 0) + (secondChecked ? 1 : 0) + (thirdChecked ? 1 : 0) > 1
            ) {
                throw new RuntimeException("不能同时点检多个点检类型!");
            }

            if (firstChecked) {
                if (
                        StringUtils.equals(currentUserInfo.getId(), existAnswer.getSecondCheckOperator())
                                || StringUtils.equals(currentUserInfo.getId(), existAnswer.getThirdCheckOperator())
                ) {
                    throw new RuntimeException("该用户已经点检其他类型，点检失败!");
                }
                checkItemAnswer.setFirstCheckOperator(currentUserInfo.getId());
            }
            if (secondChecked) {
                if (
                        StringUtils.equals(currentUserInfo.getId(), existAnswer.getFirstCheckOperator())
                                || StringUtils.equals(currentUserInfo.getId(), existAnswer.getThirdCheckOperator())
                ) {
                    throw new RuntimeException("该用户已经点检其他类型，点检失败!");
                }
                checkItemAnswer.setSecondCheckOperator(currentUserInfo.getId());
            }
            if (thirdChecked) {
                if (
                        StringUtils.equals(currentUserInfo.getId(), existAnswer.getFirstCheckOperator())
                                || StringUtils.equals(currentUserInfo.getId(), existAnswer.getSecondCheckOperator())
                ) {
                    throw new RuntimeException("该用户已经点检其他类型，点检失败!");
                }
                checkItemAnswer.setThirdCheckOperator(currentUserInfo.getId());
            }
            addCheckRecord(existAnswer, checkItemAnswer,
                    firstChecked, secondChecked, thirdChecked,
                    currentUserInfo);
            result = checkItemAnswerDao.update(checkItemAnswer);
        }
        return result;
    }


    private void addCheckRecord(CheckItemAnswer oldAnswer, CheckItemAnswer newAnswer,
                                boolean isFirstChanged,
                                boolean isSecondChanged,
                                boolean isThirdChanged,
                                CurrentUserInfo currentUserInfo) {

        CheckItem checkItem = checkItemDao.listById(newAnswer.getCheckItemId());
        if (checkItem == null) {
            return;
        }

        User user = userDao.listById(currentUserInfo.getId());
        if (user == null) {
            if(currentUserInfo.getId().equals("admin") || currentUserInfo.getId().equals("test")) {
                user = new User();
                user.setId(currentUserInfo.getId());
                user.setName(currentUserInfo.getId());
            }else{
                return;
            }
        }
        List<String> recordStr = new ArrayList<>();
//        if (oldAnswer == null) {
        if (isFirstChanged) {
            if (newAnswer.getFirstCheckVerifyResult() != null) {
                recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getFirstCheckVerifyResult() ? "合格" : "不合格"));
            }
            if (StringUtils.isNotBlank(newAnswer.getFirstCheckRemark())) {
                recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录备注", newAnswer.getFirstCheckRemark()));
            }
            switch (checkItem.getFirstCheckVerifyType()) {
                case TEXT:
                    if (StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyTextValue()))
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getFirstCheckVerifyTextValue()));
                    break;
                case NUMBER:
                    if (StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyNumberValue()))
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getFirstCheckVerifyNumberValue()));
                    break;
                case IMAGE:
                    SysFileUpload imageFile = sysFileUploadDao.selectById(newAnswer.getFirstCheckVerifyImageId());
                    if (imageFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", imageFile.getFullName()));
                    }
                    break;
                case FILE:
                    SysFileUpload fileFile = sysFileUploadDao.selectById(newAnswer.getFirstCheckVerifyImageId());
                    if (fileFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", fileFile.getFullName()));
                    }
                    break;
                case VIDEO:
                    SysFileUpload videoFile = sysFileUploadDao.selectById(newAnswer.getFirstCheckVerifyImageId());
                    if (videoFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", videoFile.getFullName()));
                    }
                    break;
            }
        } else if (isSecondChanged) {
            if (newAnswer.getSecondCheckVerifyResult() != null) {
                recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getSecondCheckVerifyResult() ? "合格" : "不合格"));
            }
            if (StringUtils.isNotBlank(newAnswer.getSecondCheckRemark())) {
                recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录备注", newAnswer.getSecondCheckRemark()));
            }
            switch (checkItem.getSecondCheckVerifyType()) {
                case TEXT:
                    if (StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyTextValue()))
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getSecondCheckVerifyTextValue()));
                    break;
                case NUMBER:
                    if (StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyNumberValue()))
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getSecondCheckVerifyNumberValue()));
                    break;
                case IMAGE:
                    SysFileUpload imageFile = sysFileUploadDao.selectById(newAnswer.getSecondCheckVerifyImageId());
                    if (imageFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", imageFile.getFullName()));
                    }
                    break;
                case FILE:
                    SysFileUpload fileFile = sysFileUploadDao.selectById(newAnswer.getSecondCheckVerifyImageId());
                    if (fileFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", fileFile.getFullName()));
                    }
                    break;
                case VIDEO:
                    SysFileUpload videoFile = sysFileUploadDao.selectById(newAnswer.getSecondCheckVerifyImageId());
                    if (videoFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", videoFile.getFullName()));
                    }
                    break;
            }
        } else if (isThirdChanged) {
            if (newAnswer.getThirdCheckVerifyResult() != null) {
                recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getThirdCheckVerifyResult() ? "合格" : "不合格"));
            }
            if (StringUtils.isNotBlank(newAnswer.getThirdCheckRemark())) {
                recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录备注", newAnswer.getThirdCheckRemark()));
            }
            switch (checkItem.getThirdCheckVerifyType()) {
                case TEXT:
                    if (StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyTextValue()))
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getThirdCheckVerifyTextValue()));
                    break;
                case NUMBER:
                    if (StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyNumberValue()))
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getThirdCheckVerifyNumberValue()));
                    break;
                case IMAGE:
                    SysFileUpload imageFile = sysFileUploadDao.selectById(newAnswer.getThirdCheckVerifyImageId());
                    if (imageFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", imageFile.getFullName()));
                    }
                    break;
                case FILE:
                    SysFileUpload fileFile = sysFileUploadDao.selectById(newAnswer.getThirdCheckVerifyImageId());
                    if (fileFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", fileFile.getFullName()));
                    }
                    break;
                case VIDEO:
                    SysFileUpload videoFile = sysFileUploadDao.selectById(newAnswer.getThirdCheckVerifyImageId());
                    if (videoFile != null) {
                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", videoFile.getFullName()));
                    }
                    break;
            }
        }
//        } else {
//            if (isFirstChanged) {
//                if (newAnswer.getFirstCheckVerifyResult() != null) {
//                    recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getFirstCheckVerifyResult() ? "合格" : "不合格"));
//                }
//                if (StringUtils.isNotBlank(newAnswer.getFirstCheckRemark())) {
//                    recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录备注", newAnswer.getFirstCheckRemark()));
//                }
//                switch (checkItem.getFirstCheckVerifyType()) {
//                    case TEXT:
//                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getFirstCheckVerifyTextValue()));
//                        break;
//                    case NUMBER:
//                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getFirstCheckVerifyNumberValue()));
//                        break;
//                    case IMAGE:
//                        SysFileUpload imageFile = sysFileUploadDao.selectById(newAnswer.getFirstCheckVerifyImageId());
//                        if (imageFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", imageFile.getFullName()));
//                        }
//                        break;
//                    case FILE:
//                        SysFileUpload fileFile = sysFileUploadDao.selectById(newAnswer.getFirstCheckVerifyImageId());
//                        if (fileFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", fileFile.getFullName()));
//                        }
//                        break;
//                    case VIDEO:
//                        SysFileUpload videoFile = sysFileUploadDao.selectById(newAnswer.getFirstCheckVerifyImageId());
//                        if (videoFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", videoFile.getFullName()));
//                        }
//                        break;
//                }
//            }else if(isSecondChanged) {
//                if (newAnswer.getSecondCheckVerifyResult() != null) {
//                    recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getSecondCheckVerifyResult() ? "合格" : "不合格"));
//                }
//                if (StringUtils.isNotBlank(newAnswer.getSecondCheckRemark())) {
//                    recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录备注", newAnswer.getSecondCheckRemark()));
//                }
//                switch (checkItem.getSecondCheckVerifyType()) {
//                    case TEXT:
//                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getSecondCheckVerifyTextValue()));
//                        break;
//                    case NUMBER:
//                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getSecondCheckVerifyNumberValue()));
//                        break;
//                    case IMAGE:
//                        SysFileUpload imageFile = sysFileUploadDao.selectById(newAnswer.getSecondCheckVerifyImageId());
//                        if (imageFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", imageFile.getFullName()));
//                        }
//                        break;
//                    case FILE:
//                        SysFileUpload fileFile = sysFileUploadDao.selectById(newAnswer.getSecondCheckVerifyImageId());
//                        if (fileFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", fileFile.getFullName()));
//                        }
//                        break;
//                    case VIDEO:
//                        SysFileUpload videoFile = sysFileUploadDao.selectById(newAnswer.getSecondCheckVerifyImageId());
//                        if (videoFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", videoFile.getFullName()));
//                        }
//                        break;
//                }
//            }else if (isThirdChanged) {
//                if (newAnswer.getThirdCheckVerifyResult() != null) {
//                    recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getThirdCheckVerifyResult() ? "合格" : "不合格"));
//                }
//                if (StringUtils.isNotBlank(newAnswer.getThirdCheckRemark())) {
//                    recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录备注", newAnswer.getThirdCheckRemark()));
//                }
//                switch (checkItem.getThirdCheckVerifyType()) {
//                    case TEXT:
//                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getThirdCheckVerifyTextValue()));
//                        break;
//                    case NUMBER:
//                        recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", newAnswer.getThirdCheckVerifyNumberValue()));
//                        break;
//                    case IMAGE:
//                        SysFileUpload imageFile = sysFileUploadDao.selectById(newAnswer.getThirdCheckVerifyImageId());
//                        if (imageFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", imageFile.getFullName()));
//                        }
//                        break;
//                    case FILE:
//                        SysFileUpload fileFile = sysFileUploadDao.selectById(newAnswer.getThirdCheckVerifyImageId());
//                        if (fileFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", fileFile.getFullName()));
//                        }
//                        break;
//                    case VIDEO:
//                        SysFileUpload videoFile = sysFileUploadDao.selectById(newAnswer.getThirdCheckVerifyImageId());
//                        if (videoFile != null) {
//                            recordStr.add(String.format("%s 操作了 %s 操作值为: %s", user.getName(), "自检记录", videoFile.getFullName()));
//                        }
//                        break;
//                }
//            }
//        }
        for (int index = 0; index < recordStr.size(); index++) {
            String record = recordStr.get(index);
            CheckItemRecord checkItemRecord = new CheckItemRecord();
            checkItemRecord.setStageRelId(newAnswer.getStageRelId());
            checkItemRecord.setCheckItemId(newAnswer.getCheckItemId());
            checkItemRecord.setOperationId(currentUserInfo.getId());
            checkItemRecord.setCheckRecord(record);
            checkItemRecordDao.insert(checkItemRecord);
        }
    }

    private boolean isFirstCheckHasChanged(CheckItemAnswer oldAnswer, CheckItemAnswer newAnswer) {
        boolean result = false;
        if (
                oldAnswer.getFirstCheckVerifyResult() != newAnswer.getFirstCheckVerifyResult()
                        || StringUtils.isNotBlank(newAnswer.getFirstCheckRemark()) && !StringUtils.equals(oldAnswer.getFirstCheckRemark(), newAnswer.getFirstCheckRemark())
                        || StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyImageId()) && !StringUtils.equals(oldAnswer.getFirstCheckVerifyImageId(), newAnswer.getFirstCheckVerifyImageId())
                        || StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyVideoId()) && !StringUtils.equals(oldAnswer.getFirstCheckVerifyVideoId(), newAnswer.getFirstCheckVerifyVideoId())
                        || StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyFileId()) && !StringUtils.equals(oldAnswer.getFirstCheckVerifyFileId(), newAnswer.getFirstCheckVerifyFileId())
                        || StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyNumberValue()) && !StringUtils.equals(oldAnswer.getFirstCheckVerifyNumberValue(), newAnswer.getFirstCheckVerifyNumberValue())
                        || StringUtils.isNotBlank(newAnswer.getFirstCheckVerifyTextValue()) && !StringUtils.equals(oldAnswer.getFirstCheckVerifyTextValue(), newAnswer.getFirstCheckVerifyTextValue())
        ) {
            result = true;
        }
        return result;
    }

    private boolean isFirstCheckHasChecked(CheckItemAnswer answer) {
        boolean result = false;
        if (answer.getFirstCheckVerifyResult() != null
                || StringUtils.isNotBlank(answer.getFirstCheckRemark()) && answer.getFirstCheckRemark() != null
                || StringUtils.isNotBlank(answer.getFirstCheckVerifyImageId()) && answer.getFirstCheckVerifyImageId() != null
                || StringUtils.isNotBlank(answer.getFirstCheckVerifyVideoId()) && answer.getFirstCheckVerifyVideoId() != null
                || StringUtils.isNotBlank(answer.getFirstCheckVerifyFileId()) && answer.getFirstCheckVerifyFileId() != null
                || StringUtils.isNotBlank(answer.getFirstCheckVerifyNumberValue()) && answer.getFirstCheckVerifyNumberValue() != null
                || StringUtils.isNotBlank(answer.getFirstCheckVerifyTextValue()) && answer.getFirstCheckVerifyTextValue() != null
        ) {
            result = true;
        }
        return result;
    }

    private boolean isSecondCheckHasChanged(CheckItemAnswer oldAnswer, CheckItemAnswer newAnswer) {
        boolean result = false;
        if (
                oldAnswer.getSecondCheckVerifyResult() != newAnswer.getSecondCheckVerifyResult()
                        || StringUtils.isNotBlank(newAnswer.getSecondCheckRemark()) && !StringUtils.equals(oldAnswer.getSecondCheckRemark(), newAnswer.getSecondCheckRemark())
                        || StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyImageId()) && !StringUtils.equals(oldAnswer.getSecondCheckVerifyImageId(), newAnswer.getSecondCheckVerifyImageId())
                        || StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyVideoId()) && !StringUtils.equals(oldAnswer.getSecondCheckVerifyVideoId(), newAnswer.getSecondCheckVerifyVideoId())
                        || StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyFileId()) && !StringUtils.equals(oldAnswer.getSecondCheckVerifyFileId(), newAnswer.getSecondCheckVerifyFileId())
                        || StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyNumberValue()) && !StringUtils.equals(oldAnswer.getSecondCheckVerifyNumberValue(), newAnswer.getSecondCheckVerifyNumberValue())
                        || StringUtils.isNotBlank(newAnswer.getSecondCheckVerifyTextValue()) && !StringUtils.equals(oldAnswer.getSecondCheckVerifyTextValue(), newAnswer.getSecondCheckVerifyTextValue())
        ) {
            result = true;
        }
        return result;
    }

    private boolean isSecondCheckHasChecked(CheckItemAnswer answer) {
        boolean result = false;
        if (answer.getSecondCheckVerifyResult() != null
                || StringUtils.isNotBlank(answer.getSecondCheckRemark()) && answer.getSecondCheckRemark() != null
                || StringUtils.isNotBlank(answer.getSecondCheckVerifyImageId()) && answer.getSecondCheckVerifyImageId() != null
                || StringUtils.isNotBlank(answer.getSecondCheckVerifyVideoId()) && answer.getSecondCheckVerifyVideoId() != null
                || StringUtils.isNotBlank(answer.getSecondCheckVerifyFileId()) && answer.getSecondCheckVerifyFileId() != null
                || StringUtils.isNotBlank(answer.getSecondCheckVerifyNumberValue()) && answer.getSecondCheckVerifyNumberValue() != null
                || StringUtils.isNotBlank(answer.getSecondCheckVerifyTextValue()) && answer.getSecondCheckVerifyTextValue() != null
        ) {
            result = true;
        }
        return result;
    }

    private boolean isThirdCheckHasChanged(CheckItemAnswer oldAnswer, CheckItemAnswer newAnswer) {
        boolean result = false;
        if (
                oldAnswer.getThirdCheckVerifyResult() != newAnswer.getThirdCheckVerifyResult()
                        || StringUtils.isNotBlank(newAnswer.getThirdCheckRemark()) && !StringUtils.equals(oldAnswer.getThirdCheckRemark(), newAnswer.getThirdCheckRemark())
                        || StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyImageId()) && !StringUtils.equals(oldAnswer.getThirdCheckVerifyImageId(), newAnswer.getThirdCheckVerifyImageId())
                        || StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyVideoId()) && !StringUtils.equals(oldAnswer.getThirdCheckVerifyVideoId(), newAnswer.getThirdCheckVerifyVideoId())
                        || StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyFileId()) && !StringUtils.equals(oldAnswer.getThirdCheckVerifyFileId(), newAnswer.getThirdCheckVerifyFileId())
                        || StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyNumberValue()) && !StringUtils.equals(oldAnswer.getThirdCheckVerifyNumberValue(), newAnswer.getThirdCheckVerifyNumberValue())
                        || StringUtils.isNotBlank(newAnswer.getThirdCheckVerifyTextValue()) && !StringUtils.equals(oldAnswer.getThirdCheckVerifyTextValue(), newAnswer.getThirdCheckVerifyTextValue())
        ) {
            result = true;
        }
        return result;
    }

    private boolean isThirdCheckHasChecked(CheckItemAnswer answer) {
        boolean result = false;
        if (answer.getThirdCheckVerifyResult() != null
                || StringUtils.isNotBlank(answer.getThirdCheckRemark()) && answer.getThirdCheckRemark() != null
                || StringUtils.isNotBlank(answer.getThirdCheckVerifyImageId()) && answer.getThirdCheckVerifyImageId() != null
                || StringUtils.isNotBlank(answer.getThirdCheckVerifyVideoId()) && answer.getThirdCheckVerifyVideoId() != null
                || StringUtils.isNotBlank(answer.getThirdCheckVerifyFileId()) && answer.getThirdCheckVerifyFileId() != null
                || StringUtils.isNotBlank(answer.getThirdCheckVerifyNumberValue()) && answer.getThirdCheckVerifyNumberValue() != null
                || StringUtils.isNotBlank(answer.getThirdCheckVerifyTextValue()) && answer.getThirdCheckVerifyTextValue() != null
        ) {
            result = true;
        }
        return result;
    }

    public CheckItemAnswerVo select(CheckItemAnswer checkItemAnswer) {
        CheckItemAnswer answer = checkItemAnswerDao.select(checkItemAnswer);

        CheckItemAnswerVo answerVo = new CheckItemAnswerVo();
        if (answer != null) {
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
        CheckItemAnswer answer1 = new CheckItemAnswer();
        answer1.setStageRelId(stageRelId);
        answer1.setCheckItemId(checkItem.getId());
        CheckItemAnswer answer = checkItemAnswerDao.select(answer1);

        boolean isChecked = answer != null && answer.getFirstCheckVerifyResult() != null &&
                answer.getSecondCheckVerifyResult() != null &&
                answer.getThirdCheckVerifyResult() != null;

        checkItem.setChecked(isChecked);
        if (isChecked) {
            checkItem.setIsGood(answer.getFirstCheckVerifyResult() && answer.getSecondCheckVerifyResult() && answer.getThirdCheckVerifyResult());
        } else {
            checkItem.setIsGood(false);
        }
    }
}
