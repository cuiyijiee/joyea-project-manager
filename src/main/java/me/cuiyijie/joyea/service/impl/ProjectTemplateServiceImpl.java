package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.main.ProjectTemplateDao;
import me.cuiyijie.joyea.domain.ProjectTemplate;
import me.cuiyijie.joyea.service.IProjectTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTemplateServiceImpl implements IProjectTemplateService {

    @Autowired
    private ProjectTemplateDao projectTemplateDao;

    @Override
    public PageInfo<ProjectTemplate> findAll(ProjectTemplate projectTemplate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectTemplate> projectTemplates = projectTemplateDao.findAll(projectTemplate);
        return new PageInfo<>(projectTemplates);
    }

    @Override
    public Integer insert(ProjectTemplate projectTemplate) {
        return projectTemplateDao.insert(projectTemplate);
    }

    @Override
    public Integer update(ProjectTemplate projectTemplate) {
        return projectTemplateDao.update(projectTemplate);
    }

    @Override
    public Integer delete(ProjectTemplate projectTemplate) {
        return projectTemplateDao.delete(projectTemplate);
    }
}
