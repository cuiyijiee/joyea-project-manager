package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.JoyeaProject;

public interface IJoyeaProjectService {

    PageInfo<JoyeaProject> pageListJoyeaProject(int pageNum, int pageSize);

}
