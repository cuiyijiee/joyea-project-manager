package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.joyea.JoyeaOperationDao;
import me.cuiyijie.joyea.dao.main.JoyeaProjectTemplateOperationDao;
import me.cuiyijie.joyea.dao.main.ProjectTemplateDao;
import me.cuiyijie.joyea.domain.JoyeaOperation;
import me.cuiyijie.joyea.domain.JoyeaProjectTemplateOperation;
import me.cuiyijie.joyea.domain.ProjectTemplate;
import me.cuiyijie.joyea.pojo.vo.JoyeaProjectTemplateOperationVo;
import me.cuiyijie.joyea.service.IProjectTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectTemplateServiceImpl implements IProjectTemplateService {

    @Autowired
    private ProjectTemplateDao projectTemplateDao;

    @Autowired
    private JoyeaOperationDao joyeaOperationDao;

    @Autowired
    private JoyeaProjectTemplateOperationDao joyeaProjectTemplateOperationDao;

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

    @Override
    public Integer addOperation(Integer templateId, String operationId, String operationNo) {
        JoyeaProjectTemplateOperation joyeaProjectTemplateOperation = new JoyeaProjectTemplateOperation();
        joyeaProjectTemplateOperation.setTemplateId(templateId);
        joyeaProjectTemplateOperation.setOperationId(operationId);
        joyeaProjectTemplateOperation.setOperationNo(operationNo);
        return joyeaProjectTemplateOperationDao.insert(joyeaProjectTemplateOperation);
    }

    @Override
    public List<JoyeaProjectTemplateOperationVo> findAllOperation(Integer templateId) {
        JoyeaProjectTemplateOperation joyeaProjectTemplateOperation = new JoyeaProjectTemplateOperation();
        joyeaProjectTemplateOperation.setTemplateId(templateId);
        List<JoyeaProjectTemplateOperation> operationIds = joyeaProjectTemplateOperationDao.findByTemplateId(joyeaProjectTemplateOperation);

        List<JoyeaProjectTemplateOperationVo> results = new ArrayList<>();
        for (int index = 0; index < operationIds.size(); index++) {
            JoyeaProjectTemplateOperation temp = operationIds.get(index);
            JoyeaOperation joyeaOperation = joyeaOperationDao.findByIdAndNo(temp.getOperationId(), temp.getOperationNo());

            JoyeaProjectTemplateOperationVo joyeaProjectTemplateOperationVo = new JoyeaProjectTemplateOperationVo();
            BeanUtils.copyProperties(joyeaOperation, joyeaProjectTemplateOperationVo);
            joyeaProjectTemplateOperationVo.setId(temp.getId());
            joyeaProjectTemplateOperationVo.setTemplateId(temp.getTemplateId());
            results.add(joyeaProjectTemplateOperationVo);
        }
        return results;
    }

    @Override
    public Integer deleteOperation(Integer templateId, String operationId, String operationNo) {
        JoyeaProjectTemplateOperation joyeaProjectTemplateOperation = new JoyeaProjectTemplateOperation();
        joyeaProjectTemplateOperation.setTemplateId(templateId);
        joyeaProjectTemplateOperation.setOperationId(operationId);
        joyeaProjectTemplateOperation.setOperationNo(operationNo);
        return joyeaProjectTemplateOperationDao.delete(joyeaProjectTemplateOperation);
    }
}
