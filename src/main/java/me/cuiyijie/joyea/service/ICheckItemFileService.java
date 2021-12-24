package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.model.SysFileUpload;

import java.util.List;

public interface ICheckItemFileService {

    Integer insert(String checkItemId, String fileId, Integer fileType, UserFileType userFileType);

    List<SysFileUpload> selectByCheckItemId(String checkItemId, Integer fileType, UserFileType userFileType);

    Integer delete(Integer id);
}
