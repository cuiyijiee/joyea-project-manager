package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.CheckItemFormColumnNameDao;
import me.cuiyijie.joyea.domain.CheckItemFormColumnName;
import me.cuiyijie.joyea.service.ICheckItemFormColumnNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemFormColumnNameServiceImpl implements ICheckItemFormColumnNameService {

    @Autowired
    private CheckItemFormColumnNameDao checkItemFormColumnNameDao;

    @Override
    public List<CheckItemFormColumnName> listAll(String operationId) {
        return checkItemFormColumnNameDao.selectByOperationId(operationId);
    }
}
