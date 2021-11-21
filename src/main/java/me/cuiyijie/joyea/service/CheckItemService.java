package me.cuiyijie.joyea.service;

import io.swagger.models.auth.In;
import me.cuiyijie.joyea.dao.main.CheckItemDao;
import me.cuiyijie.joyea.dao.main.TemplateDao;
import me.cuiyijie.joyea.enums.TemplateLevelType;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private CheckItemTagService checkItemTagService;

    public List<CheckItem> listChild(Integer id) {
        List<CheckItem> checkItems = checkItemDao.listChild(id);
        for (int index = 0; index < checkItems.size(); index++) {
            CheckItem checkItem1 = checkItems.get(index);
            List<CheckItemTag> checkItemTags = checkItemTagService.listByCheckItemId(checkItem1.getId());
            checkItem1.setTags(checkItemTags);
        }
        return checkItems;
    }

    public List<CheckItem> listAll(CheckItemVo checkItemVo) {
        List<CheckItem> checkItems = checkItemDao.listAll(checkItemVo);
        for (int index = 0; index < checkItems.size(); index++) {
            CheckItem checkItem1 = checkItems.get(index);
            List<CheckItemTag> checkItemTags = checkItemTagService.listByCheckItemId(checkItem1.getId());
            checkItem1.setTags(checkItemTags);
        }
        return checkItems;
    }

    public Integer update(CheckItem checkItem) {
        Integer result = checkItemDao.update(checkItem);
        checkItemTagService.deleteAllCheckItemTagRel(checkItem.getId());
        if (checkItem.getTags() != null) {
            for (int index = 0; index < checkItem.getTags().size(); index++) {
                CheckItemTag checkItemTag = checkItem.getTags().get(index);
                checkItemTagService.addCheckItemTagRel(checkItem.getId(),checkItemTag.getId());
                //checkItemTagService.insert(checkItem.getTags().get(index));
            }
        }
        return checkItemDao.update(checkItem);
    }

    @Transactional
    public Integer insert(CheckItem checkItem) {
        Integer result = checkItemDao.insert(checkItem);
        if (checkItem.getTags() != null) {
            for (int index = 0; index < checkItem.getTags().size(); index++) {
                CheckItemTag checkItemTag = checkItem.getTags().get(index);
                checkItemTagService.addCheckItemTagRel(checkItem.getId(),checkItemTag.getId());
                //checkItemTagService.insert(checkItem.getTags().get(index));
            }
        }
        return result;
    }

    public Integer delete(CheckItem checkItem) {
        return checkItemDao.delete(checkItem);
    }

    public void addCheckItemRel(Integer pid, Integer id) {
        CheckItem checkItem = checkItemDao.listById(id);
        if (checkItem == null) {
            throw new RuntimeException("被关联的点检项不存在！");
        }
        Template template = templateDao.listById(pid);
        if (template == null || template.getLevelType() == null || template.getLevelType() != TemplateLevelType.OPERATION) {
            throw new RuntimeException("被关联的工序不存在！");
        } else {
            templateDao.addTemplateRel(pid, id);
        }
    }

    public Integer deleteCheckItemRel(Integer pid, Integer id) {
        return templateDao.deleteTemplateRel(pid, id);
    }

    public Integer selectCheckItemRel(Integer checkItemId) {
        return checkItemDao.selectCheckItemRel(checkItemId);
    }

    public Integer updateState(@Param("item") CheckItem checkItem) {
        return checkItemDao.updateState(checkItem);
    }
}
