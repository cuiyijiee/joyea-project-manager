package me.cuiyijie.joyea.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.CheckItemDao;
import me.cuiyijie.joyea.dao.ProjectStageDao;
import me.cuiyijie.joyea.dao.ProjectStageOperationDao;
import me.cuiyijie.joyea.model.*;
import me.cuiyijie.joyea.model.StageProduct;
import me.cuiyijie.joyea.model.vo.ProjectStageVo;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/30 11:18
 */
@Slf4j
@Service
public class ProjectStageService {

    @Autowired
    private ProjectStageDao projectStageDao;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ProjectStageOperationDao projectStageOperationDao;

    @Autowired
    private CheckItemDao checkItemDao;

    @Transactional
    public void insert(ProjectStage projectStage) {

    }

    public Integer countProjectStage(Integer projectId){
        return projectStageDao.countProjectStage(projectId);
    }

    @Transactional
    public void delete(ProjectStage projectStage) {
        projectStageDao.deleteStageProductByStageId(projectStage.getId());
        projectStageDao.delete(projectStage.getId());
    }

    @Transactional
    public void addProductOperation(ProjectStageOperation projectStageOperation) {
        Template parentTemplate = templateService.listById(projectStageOperation.getParentId());
        if (parentTemplate == null) {
            throw new RuntimeException("父文件夹不存在！");
        }
        Template operation = templateService.listById(projectStageOperation.getOperationId());
        if (operation == null) {
            throw new RuntimeException("当前工序不存在！");
        }
        projectStageOperation.setParentName(parentTemplate.getName());
        projectStageOperation.setOperationName(operation.getName());
        projectStageOperationDao.insert(projectStageOperation);
    }

    @Transactional
    public void addProductOperations(List<ProjectStageOperation> projectStageOperations) {
        for (int index = 0; index < projectStageOperations.size(); index++) {
            ProjectStageOperation projectStageOperation = projectStageOperations.get(index);
            List<String> paramsCheck = Lists.newArrayList("stageRelId:阶段-产品关联ID（stageRelId）", "parentId:工序父文件夹id（parentId）", "operationId:工序id（operationId）");
            String errorMsg = CheckParamsUtil.checkAll(projectStageOperation, paramsCheck, null, null);
            if (errorMsg != null) {
                throw new RuntimeException("请求参数缺少：" + errorMsg);
            }
            Template parentTemplate = templateService.listById(projectStageOperation.getParentId());
            if (parentTemplate == null) {
                throw new RuntimeException("父文件夹不存在！");
            }
            Template operation = templateService.listById(projectStageOperation.getOperationId());
            if (operation == null) {
                throw new RuntimeException("当前工序不存在！");
            }
            projectStageOperation.setParentName(parentTemplate.getName());
            projectStageOperation.setOperationName(operation.getName());
            projectStageOperationDao.insert(projectStageOperation);
        }
    }

    @Transactional
    public void deleteOperation(ProjectStageOperation projectStageOperation) {
        projectStageOperationDao.delete(projectStageOperation);
        projectStageOperationDao.deleteRelByOperationId(projectStageOperation.getId());
    }

    public List<ProjectStageOperation> listOperation(StageProduct stageProduct) {
        return projectStageOperationDao.list(stageProduct);
    }

    public List<CheckItem> listCheckItems(ProjectStageOperation projectStageOperation) {
        return projectStageOperationDao.listCheckItems(projectStageOperation.getId());
    }
}
