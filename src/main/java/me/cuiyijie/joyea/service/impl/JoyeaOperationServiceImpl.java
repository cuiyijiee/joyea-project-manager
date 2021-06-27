package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.joyea.JoyeaOperationDao;
import me.cuiyijie.joyea.domain.JoyeaOperation;
import me.cuiyijie.joyea.service.IJoyeaOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaOperationServiceImpl implements IJoyeaOperationService {


    @Autowired
    private JoyeaOperationDao joyeaManufactureTaskDao;

    @Override
    public List<JoyeaOperation> findByManufacturerOrderId(String manufactureId) {
        return joyeaManufactureTaskDao.selectByManufactureOrder(manufactureId);
    }
}
