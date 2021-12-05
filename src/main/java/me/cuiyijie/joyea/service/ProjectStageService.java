package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.main.CheckItemDao;
import me.cuiyijie.joyea.dao.main.ProjectStageDao;
import me.cuiyijie.joyea.dao.main.ProjectStageOperationDao;
import me.cuiyijie.joyea.model.*;
import me.cuiyijie.joyea.model.StageProduct;
import me.cuiyijie.joyea.model.vo.ProjectStageVo;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yjcui3
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
        projectStageDao.insert(projectStage);
        if (projectStage.getId() != null) {
            if (projectStage.getContainsProject() != null && projectStage.getContainsProject()) {
                projectStageDao.insertStageProduct(projectStage.getId(), -1, true);
            }
            if (projectStage.getProducts() != null && projectStage.getProducts().size() > 0) {
                for (int index = 0; index < projectStage.getProducts().size(); index++) {
                    Product product = projectStage.getProducts().get(index);
                    projectStageDao.insertStageProduct(projectStage.getId(), product.getId(), false);
                }
            } else {
                log.info("该工程阶段不包含产品，无需处理");
            }
        } else {
            throw new RuntimeException("插入项目阶段失败！");
        }
    }

//    @Transactional
//    public void update(ProjectStage projectStage) {
//        projectStageDao.update(projectStage);
//        projectStageDao.deleteStageProductByStageId(projectStage.getId());
//        if (projectStage.getId() != null) {
//            if (projectStage.getProducts() != null && projectStage.getProducts().size() > 0) {
//                for (int index = 0; index < projectStage.getProducts().size(); index++) {
//                    Product product = projectStage.getProducts().get(index);
//                    projectStageDao.insertStageProduct(projectStage.getId(), product.getId());
//                }
//            } else {
//                log.info("该工程阶段不包含产品，无需处理");
//            }
//        } else {
//            throw new RuntimeException("更新项目阶段失败！");
//        }
//    }

    public PageInfo<ProjectStage> list(ProjectStageVo projectStageVo) {
        PageHelper.startPage(projectStageVo.getPageNum(), projectStageVo.getPageSize());
        PageInfo<ProjectStage> pageInfo = new PageInfo<ProjectStage>(projectStageDao.list(projectStageVo));
        PageHelper.clearPage();
        for (int index = 0; index < pageInfo.getList().size(); index++) {
            ProjectStage tmp = pageInfo.getList().get(index);
            tmp.setProducts(projectStageDao.listProductByStageId(tmp.getId()));
        }
        return pageInfo;
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

        //重新建立索引
        List<CheckItem> checkItems = checkItemDao.listChild(operation.getId());
        for (int index = 0; index < checkItems.size(); index++) {
            projectStageOperationDao.insertCheckItemRel(projectStageOperation.getId(), checkItems.get(index).getId());
        }
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

            //重新建立索引
            List<CheckItem> checkItems = checkItemDao.listChild(operation.getId());
            for (int jndex = 0; jndex < checkItems.size(); jndex++) {
                projectStageOperationDao.insertCheckItemRel(projectStageOperation.getId(), checkItems.get(jndex).getId());
            }
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
