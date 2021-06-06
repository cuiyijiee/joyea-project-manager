package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.JoyeaManufactureTaskDao;
import me.cuiyijie.joyea.domain.JoyeaManufactureTask;
import me.cuiyijie.joyea.service.JoyeaManufactureTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaManufactureTaskServiceImpl implements JoyeaManufactureTaskService {


    @Autowired
    private JoyeaManufactureTaskDao joyeaManufactureTaskDao;

    @Override
    public List<JoyeaManufactureTask> findByManufacturerOrderId(String manufactureId) {
        return joyeaManufactureTaskDao.selectByManufactureOrder(manufactureId);
    }
}
