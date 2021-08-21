package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.JoyeaUrs;
import me.cuiyijie.joyea.domain.JoyeaUrsSettings;

public interface IJoyeaUrsService {

    PageInfo<JoyeaUrs> selectByProjectNumber(String projectNumber, Integer pageSize, Integer pageNum);

    Integer updateUrsSettings(JoyeaUrsSettings joyeaUrsSettings);

}
