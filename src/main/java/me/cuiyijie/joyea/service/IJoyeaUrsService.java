package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.JoyeaUrs;

public interface IJoyeaUrsService {

    PageInfo<JoyeaUrs> selectByProjectNumber(String projectNumber, Integer pageSize, Integer pageNum);

}
