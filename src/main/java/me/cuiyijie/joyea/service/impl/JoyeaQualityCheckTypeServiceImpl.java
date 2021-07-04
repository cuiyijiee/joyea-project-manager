package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.joyea.JoyeaQualityCheckTypeDao;
import me.cuiyijie.joyea.domain.JoyeaQualityCheckType;
import me.cuiyijie.joyea.service.IJoyeaQualityCheckTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaQualityCheckTypeServiceImpl implements IJoyeaQualityCheckTypeService {

    @Autowired
    private JoyeaQualityCheckTypeDao joyeaQualityCheckTypeDao;

    @Override
    public List<JoyeaQualityCheckType> listAll() {
        return joyeaQualityCheckTypeDao.select();
    }
}
