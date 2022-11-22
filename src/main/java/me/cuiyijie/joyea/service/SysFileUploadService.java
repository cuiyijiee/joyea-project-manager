package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.SysFileUploadDao;
import me.cuiyijie.joyea.model.SysFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/30 15:21
 */
@Service
public class SysFileUploadService {

    @Autowired
    private SysFileUploadDao sysFileUploadDao;

    public Integer insert(SysFileUpload sysFileUpload) {
        return sysFileUploadDao.insert(sysFileUpload);
    }

}
