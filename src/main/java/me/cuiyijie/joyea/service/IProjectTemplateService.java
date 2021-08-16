package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.ProjectTemplate;

public interface IProjectTemplateService {

    PageInfo<ProjectTemplate> findAll(ProjectTemplate projectTemplate, Integer pageNum, Integer pageSize);

    Integer insert(ProjectTemplate projectTemplate);

    Integer update(ProjectTemplate projectTemplate);

    Integer delete(ProjectTemplate projectTemplate);

}
