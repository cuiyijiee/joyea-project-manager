package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.CheckItemAnswerDao;
import me.cuiyijie.joyea.model.CheckItemAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yjcui3
 * @Date: 2021/12/18 23:28
 */
@Service
public class CheckItemAnswerService {

    @Autowired
    private CheckItemAnswerDao checkItemAnswerDao;

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

    public CheckItemAnswer select(CheckItemAnswer checkItemAnswer) {
        return checkItemAnswerDao.select(checkItemAnswer);
    }

    public CheckItemAnswer selectById(CheckItemAnswer checkItemAnswer) {
        return checkItemAnswerDao.selectById(checkItemAnswer.getId());
    }

}
