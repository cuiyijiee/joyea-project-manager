package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.CheckItemDao;
import me.cuiyijie.joyea.model.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;


    public List<CheckItem> listAll() {
        return checkItemDao.listAll();
    }

    public Integer update(CheckItem checkItem) {
        return checkItemDao.update(checkItem);
    }

    public Integer insert(CheckItem checkItem) {
        return checkItemDao.insert(checkItem);
    }

    public Integer delete(CheckItem checkItem) {
        return checkItemDao.delete(checkItem);
    }

}
