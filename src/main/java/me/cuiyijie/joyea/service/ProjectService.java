package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.ProjectDao;
import me.cuiyijie.joyea.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public Page<Project> list(Project project, Integer pageNum, Integer pageSize) {
        Page<Project> projectPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if (project.getProjectName() != null && project.getProjectName().length() > 0) {
            queryWrapper = queryWrapper.like("PROJECTNAME", project.getProjectName())
                    .or()
                    .like("FNUMBER", project.getProjectName());
        }
        return projectDao.selectPage(projectPage, queryWrapper);
    }

    public Integer insert(Project project) {
        return null;
    }

    public Integer update(Project project) {
        return null;
    }

}
