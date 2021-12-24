package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.SysFileUploadDao;
import me.cuiyijie.joyea.model.SysFileUpload;
import me.cuiyijie.joyea.service.ISysFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysFileUploadServiceImpl implements ISysFileUploadService {

    @Autowired
    private SysFileUploadDao sysFileUploadDao;

    @Override
    public Integer insert(SysFileUpload sysFileUpload) {
        return sysFileUploadDao.insert(sysFileUpload);
    }
}
