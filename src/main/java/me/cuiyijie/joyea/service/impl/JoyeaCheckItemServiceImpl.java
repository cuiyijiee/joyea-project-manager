package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.joyea.JoyeaCheckItemDao;
import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.service.IJoyeaCheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaCheckItemServiceImpl implements IJoyeaCheckItemService {

    @Autowired
    private JoyeaCheckItemDao joyeaCheckItemDao;

    @Override
    public List<JoyeaCheckItem> findByOperationIdAndNo(String operationId, String operationNo) {
        return joyeaCheckItemDao.selectByOperationIdAndOperationNo(operationId,operationNo);
    }
}
