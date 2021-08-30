package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.ProjectTemplate;
import me.cuiyijie.joyea.pojo.vo.JoyeaProjectTemplateOperationVo;

import java.util.List;

public interface IProjectTemplateService {

    PageInfo<ProjectTemplate> findAll(ProjectTemplate projectTemplate, Integer pageNum, Integer pageSize);

    Integer insert(ProjectTemplate projectTemplate);

    Integer update(ProjectTemplate projectTemplate);

    Integer delete(ProjectTemplate projectTemplate);

    Integer addOperation(Integer templateId, String operationId, String operationNo);

    List<JoyeaProjectTemplateOperationVo> findAllOperation(Integer templateId);

    Integer deleteOperation(Integer templateId, String operationId, String operationNo);

}
