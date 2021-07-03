package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.SysFileUploadDao;
import me.cuiyijie.joyea.service.ISysFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysFileUpload implements ISysFileUploadService {

    @Autowired
    private SysFileUploadDao sysFileUploadDao;

    @Override
    public Integer insert(me.cuiyijie.joyea.domain.SysFileUpload sysFileUpload) {
        return sysFileUploadDao.insert(sysFileUpload);
    }
}
