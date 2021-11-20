package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.CheckItemDao;
import me.cuiyijie.joyea.dao.main.TemplateDao;
import me.cuiyijie.joyea.enums.TemplateLevelType;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
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
        return checkItemDao.listChild(id);
    }

    public List<CheckItem> listAll(CheckItemVo checkItemVo) {
        List<CheckItem> checkItems = checkItemDao.listAll(checkItemVo);
//        for (int index = 0; index < checkItems.size(); index++) {
//            CheckItem checkItem1 = checkItems.get(index);
//            CheckItemTag checkItemTag = new CheckItemTag();
//            checkItemTag.setCheckItemId(checkItem1.getId());
//            List<CheckItemTag> checkItemTags = checkItemTagService.listAll(checkItemTag);
//            checkItem1.setTags(checkItemTags);
//        }
        return checkItems;
    }

    public Integer update(CheckItem checkItem) {
        return checkItemDao.update(checkItem);
    }

    @Transactional
    public Integer insert(CheckItem checkItem) {
        if (checkItem.getTags() != null) {
            for (int index = 0; index < checkItem.getTags().size(); index++) {
                checkItemTagService.insert(checkItem.getTags().get(index));
            }
        }
        return checkItemDao.insert(checkItem);
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
}
