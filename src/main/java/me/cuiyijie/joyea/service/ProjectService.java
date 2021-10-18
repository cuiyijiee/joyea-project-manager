package me.cuiyijie.joyea.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import me.cuiyijie.joyea.dao.main.ProjectDao;
import me.cuiyijie.joyea.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public Page<Project> list(Project project, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        Page<Project> result = (Page<Project>) projectDao.list(project);
        return result;
    }

    public Integer insert(Project project) {
        return projectDao.insert(project);
    }

    public Integer update(Project project) {
        return projectDao.update(project);
    }

}
