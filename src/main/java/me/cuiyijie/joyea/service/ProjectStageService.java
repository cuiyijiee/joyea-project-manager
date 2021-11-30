package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.main.ProjectStageDao;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.ProjectStage;
import me.cuiyijie.joyea.model.vo.ProjectStageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yjcui3
 * @Date: 2021/11/30 11:18
 */
@Slf4j
@Service
public class ProjectStageService {

    @Autowired
    private ProjectStageDao projectStageDao;

    @Transactional
    public void insert(ProjectStage projectStage) {
        projectStageDao.insert(projectStage);
        if (projectStage.getId() != null) {
            if (projectStage.getProducts() != null && projectStage.getProducts().size() > 0) {
                for (int index = 0; index < projectStage.getProducts().size(); index++) {
                    Product product = projectStage.getProducts().get(index);
                    projectStageDao.insertStageProduct(projectStage.getId(), product.getId());
                }
            } else {
                log.info("该工程阶段不包含产品，无需处理");
            }
        } else {
            throw new RuntimeException("插入项目阶段失败！");
        }
    }

    @Transactional
    public void update(ProjectStage projectStage) {
        projectStageDao.update(projectStage);
        projectStageDao.deleteStageProductByStageId(projectStage.getId());
        if (projectStage.getId() != null) {
            if (projectStage.getProducts() != null && projectStage.getProducts().size() > 0) {
                for (int index = 0; index < projectStage.getProducts().size(); index++) {
                    Product product = projectStage.getProducts().get(index);
                    projectStageDao.insertStageProduct(projectStage.getId(), product.getId());
                }
            } else {
                log.info("该工程阶段不包含产品，无需处理");
            }
        } else {
            throw new RuntimeException("更新项目阶段失败！");
        }
    }

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
}
