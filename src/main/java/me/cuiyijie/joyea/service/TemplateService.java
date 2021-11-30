package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.TemplateDao;
import me.cuiyijie.joyea.enums.TemplateLevelType;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.TemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<TemplateVo> listAllOperation() {
        return templateDao.listAllOperation();
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

    public Integer selectChildCount(Integer templateId){
        return templateDao.selectChildCount(templateId);
    }
}
