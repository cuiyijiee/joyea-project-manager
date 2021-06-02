package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.domain.JoyeaProject;
import me.cuiyijie.joyea.service.JoyeaProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JoyeaProjectServiceImpl implements JoyeaProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public PageInfo<JoyeaProject> pageListJoyeaProject(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JoyeaProject> allProject = projectDao.getProjectList();
        return new PageInfo<>(allProject);
    }
}
