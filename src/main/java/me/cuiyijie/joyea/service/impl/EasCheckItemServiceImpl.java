package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.EasCheckItemSettingDao;
import me.cuiyijie.joyea.domain.EasCheckItemSetting;
import me.cuiyijie.joyea.service.IEasCheckItemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EasCheckItemServiceImpl implements IEasCheckItemSettingService {

    @Autowired
    private EasCheckItemSettingDao easOperationSettingDao;

    @Override
    public EasCheckItemSetting select(String operationId, String operationName) {
        return easOperationSettingDao.select(operationId, operationName);
    }

    @Override
    public Integer insert(EasCheckItemSetting easOperationSetting) {
        return easOperationSettingDao.insert(easOperationSetting);
    }

    @Override
    public Integer update(EasCheckItemSetting easOperationSetting) {
        return easOperationSettingDao.update(easOperationSetting);
    }
}
