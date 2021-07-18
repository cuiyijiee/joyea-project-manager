package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.SysFileUpload;

import java.util.List;

public interface ICheckItemFileService {

    Integer insert(String checkItemId,String fileId);

    List<SysFileUpload> selectByCheckItemId(String checkItemId,Integer fileType);

    Integer delete(Integer id );
}
