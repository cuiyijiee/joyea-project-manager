package me.cuiyijie.joyea.service;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.main.TemplateDao;
import me.cuiyijie.joyea.enums.TemplateLevelType;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.TemplateVo;
import me.cuiyijie.joyea.pojo.request.TransOperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TemplateService {

    @Autowired
    private TemplateDao templateDao;

    public Template listById(Integer id) {
        return templateDao.listById(id);
    }

    public List<Template> listAll(Template template) {
        return templateDao.listAll(template);
    }

    public List<TemplateVo> listAllOperation(Template template) {
        return templateDao.listAllOperation(template);
    }


    public List<Template> listChild(Integer id) {
        return templateDao.listChild(id);
    }

    public Integer update(Template template) {
        return templateDao.update(template);
    }

    @Transactional
    public void insert(TemplateVo templateVo) {
        if (templateVo.getPid() == null &&
                templateVo.getLevelType() != null && templateVo.getLevelType() == TemplateLevelType.DIR &&
                templateVo.getIsRoot() != null && templateVo.getIsRoot()) {
            throw new RuntimeException("levelType == 1时必须指定父级文件夹pid 或设置为根节点");
        }
        templateDao.insert(templateVo);
        if (templateVo.getPid() != null) {
            Template template = templateDao.listById(templateVo.getPid());
            if (template != null) {
                templateDao.addTemplateRel(templateVo.getPid(), templateVo.getId());
            } else {
                throw new RuntimeException("父节点不存在，无法关联！");
            }
        }
    }

    public Integer delete(Template template) {
        return templateDao.delete(template);
    }

    public Integer deleteRelByCid(Template template) {
        return templateDao.deleteRelByCid(template.getId());
    }

    public Integer addTemplateRel(Integer pid, Integer cid) {
        Template pTemplate = templateDao.listById(pid);
        if (pTemplate == null || pTemplate.getLevelType() == null || pTemplate.getLevelType() != TemplateLevelType.DIR) {
            throw new RuntimeException("父节点不存在或者不是文件夹类型");
        }
        Template cTemplate = templateDao.listById(cid);
        if (cTemplate == null) {
            throw new RuntimeException("待关联工序不存在！");
        }
        return templateDao.addTemplateRel(pid, cid);
    }

    public Integer selectChildCount(Integer templateId) {
        return templateDao.selectChildCount(templateId);
    }


    @Transactional
    public void deleteTemplate(Template template) {
        //判断要删除的节点下有没有子节点，如果有子节点不允许删除
        Integer childCount = selectChildCount(template.getId());
        if (childCount > 0) {
            throw new RuntimeException("删除节点，存在子节点无法删除");
        }
        deleteRelByCid(template);
        delete(template);
    }

    @Transactional
    public void batchDeleteTemplate(List<Integer> ids) {
        for (int index = 0; index < ids.size(); index++) {
            Template template = new Template();
            template.setId(ids.get(index));
            deleteTemplate(template);
        }
    }
}
