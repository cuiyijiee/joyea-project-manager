package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> list(Project project) {
        return projectDao.list(project);
    }

    public Integer insert(Project project) {
        return projectDao.insert(project);
    }

    public Integer update(Project project) {
        return projectDao.update(project);
    }

}
