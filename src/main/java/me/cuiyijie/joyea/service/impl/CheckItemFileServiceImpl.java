package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.CheckItemFileDao;
import me.cuiyijie.joyea.domain.SysFileUpload;
import me.cuiyijie.joyea.service.ICheckItemFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemFileServiceImpl implements ICheckItemFileService {

    @Autowired
    private CheckItemFileDao checkItemFileDao;

    @Override
    public Integer insert(String checkItemId, String fileId, Integer fileType) {
        return checkItemFileDao.insert(checkItemId, fileId, fileType);
    }

    @Override
    public List<SysFileUpload> selectByCheckItemId(String checkItemId, Integer fileType) {
        return checkItemFileDao.selectByCheckItemId(checkItemId, fileType);
    }

    @Override
    public Integer delete(Integer id) {
        return checkItemFileDao.delete(id);
    }
}
