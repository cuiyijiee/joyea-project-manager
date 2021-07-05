package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.CheckItemFormSettingDao;
import me.cuiyijie.joyea.domain.CheckItemFormSetting;
import me.cuiyijie.joyea.service.ICheckItemFormSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemFormSettingServiceImpl implements ICheckItemFormSettingService {

    @Autowired
    private CheckItemFormSettingDao checkItemFormColumnNameDao;

    @Override
    public List<CheckItemFormSetting> listAll(String checkItemId) {
        return checkItemFormColumnNameDao.selectByCheckItemId(checkItemId);
    }
}
