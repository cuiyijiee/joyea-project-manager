package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.joyea.JoyeaCheckItemDao;
import me.cuiyijie.joyea.dao.main.MainCheckItemDao;
import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.service.IJoyeaCheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaCheckItemServiceImpl implements IJoyeaCheckItemService {

    @Autowired
    private JoyeaCheckItemDao joyeaCheckItemDao;

    @Autowired
    private MainCheckItemDao mainCheckItemDao;

    @Override
    public List<JoyeaCheckItem> findByOperationIdAndNo(String operationId, String operationNo) {
        JoyeaCheckItem selectParams = new JoyeaCheckItem();
        selectParams.setOperationId(operationId);
        selectParams.setOperationNo(operationNo);
        List<JoyeaCheckItem> resultCheckItems = mainCheckItemDao.list(selectParams);
        if (resultCheckItems.size() == 0) {
            resultCheckItems = joyeaCheckItemDao.selectByOperationIdAndOperationNo(operationId, operationNo);
            //mainCheckItemDao.insertMany(resultCheckItems);

            resultCheckItems.stream().forEach(item -> {
                mainCheckItemDao.insert(item);
            });

        }
        return resultCheckItems;
    }

    @Override
    public Integer add(JoyeaCheckItem joyeaCheckItem) {
        return mainCheckItemDao.insert(joyeaCheckItem);
    }

    @Override
    public Integer update(JoyeaCheckItem joyeaCheckItem) {
        return mainCheckItemDao.update(joyeaCheckItem);
    }

    @Override
    public Integer delete(JoyeaCheckItem joyeaCheckItem) {
        return mainCheckItemDao.delete(joyeaCheckItem.getId());
    }
}
