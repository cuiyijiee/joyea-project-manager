package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.dao.main.CheckItemFileDao;
import me.cuiyijie.joyea.model.SysFileUpload;
import me.cuiyijie.joyea.service.ICheckItemFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemFileServiceImpl implements ICheckItemFileService {

    @Autowired
    private CheckItemFileDao checkItemFileDao;

    @Override
    public Integer insert(String checkItemId, String fileId, Integer fileType,UserFileType userFileType) {
        return checkItemFileDao.insert(checkItemId, fileId, fileType,userFileType);
    }

    @Override
    public List<SysFileUpload> selectByCheckItemId(String checkItemId, Integer fileType, UserFileType userFileType) {
        return checkItemFileDao.selectByCheckItemId(checkItemId, fileType,userFileType);
    }

    @Override
    public Integer delete(Integer id) {
        return checkItemFileDao.delete(id);
    }
}
