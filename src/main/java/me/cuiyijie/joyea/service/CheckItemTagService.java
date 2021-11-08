package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.CheckItemTagDao;
import me.cuiyijie.joyea.model.CheckItemTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/11/8 14:19
 */
@Service
public class CheckItemTagService {

    @Autowired
    private CheckItemTagDao checkItemTagDao;


    public List<CheckItemTag> listAll(CheckItemTag checkItemTag) {
        return checkItemTagDao.listAll(checkItemTag);
    }

    public Integer update(CheckItemTag checkItemTag) {
        return checkItemTagDao.update(checkItemTag);
    }

    public Integer insert(CheckItemTag checkItemTag) {
        return checkItemTagDao.insert(checkItemTag);
    }

    public Integer delete(CheckItemTag checkItemTag) {
        return checkItemTagDao.delete(checkItemTag);
    }

}
