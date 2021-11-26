package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.CheckItemDao;
import me.cuiyijie.joyea.dao.main.TemplateDao;
import me.cuiyijie.joyea.enums.*;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Autowired
    private TemplateDao templateDao;

    @Autowired
    private CheckItemTagService checkItemTagService;
    @Autowired
    private CheckItemPropertyService checkItemPropertyService;

    public List<CheckItem> listChild(Integer id) {
        List<CheckItem> checkItems = checkItemDao.listChild(id);
        for (int index = 0; index < checkItems.size(); index++) {
            CheckItem checkItem1 = checkItems.get(index);
            List<CheckItemTag> checkItemTags = checkItemTagService.listByCheckItemId(checkItem1.getId());
            checkItem1.setTags(checkItemTags);

            Map<CheckItemPropertyType, List<Enum<?>>> propertyMap = checkItemPropertyService.getCheckItemProperty(checkItem1.getId(), null);
            checkItem1.setCheckCategoryTypes(propertyMap.get(CheckItemPropertyType.CATEGORY).stream().map(item -> (CheckCategoryType) item).collect(Collectors.toList()));
            checkItem1.setCheckModuleTypes(propertyMap.get(CheckItemPropertyType.MODULE).stream().map(item -> (CheckModuleType) item).collect(Collectors.toList()));
            checkItem1.setCheckStageTypes(propertyMap.get(CheckItemPropertyType.STAGE).stream().map(item -> (CheckStageType) item).collect(Collectors.toList()));
        }
        return checkItems;
    }

    public List<CheckItem> listAll(CheckItemVo checkItemVo) {
        List<CheckItem> checkItems = checkItemDao.listAll(checkItemVo);
        for (int index = 0; index < checkItems.size(); index++) {
            CheckItem checkItem1 = checkItems.get(index);
            List<CheckItemTag> checkItemTags = checkItemTagService.listByCheckItemId(checkItem1.getId());
            checkItem1.setTags(checkItemTags);

            Map<CheckItemPropertyType, List<Enum<?>>> propertyMap = checkItemPropertyService.getCheckItemProperty(checkItem1.getId(), null);
            checkItem1.setCheckCategoryTypes(propertyMap.get(CheckItemPropertyType.CATEGORY).stream().map(item -> (CheckCategoryType) item).collect(Collectors.toList()));
            checkItem1.setCheckModuleTypes(propertyMap.get(CheckItemPropertyType.MODULE).stream().map(item -> (CheckModuleType) item).collect(Collectors.toList()));
            checkItem1.setCheckStageTypes(propertyMap.get(CheckItemPropertyType.STAGE).stream().map(item -> (CheckStageType) item).collect(Collectors.toList()));

        }
        return checkItems;
    }

    public Integer update(CheckItem checkItem) {
        Integer result = checkItemDao.update(checkItem);
        checkItemTagService.deleteAllCheckItemTagRel(checkItem.getId());
        if (checkItem.getTags() != null) {
            for (int index = 0; index < checkItem.getTags().size(); index++) {
                CheckItemTag checkItemTag = checkItem.getTags().get(index);
                checkItemTagService.addCheckItemTagRel(checkItem.getId(), checkItemTag.getId());
                //checkItemTagService.insert(checkItem.getTags().get(index));
                checkItemPropertyService.updateCheckItemProperty(checkItem);
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
                checkItemTagService.addCheckItemTagRel(checkItem.getId(), checkItemTag.getId());
                //checkItemTagService.insert(checkItem.getTags().get(index));
                checkItemPropertyService.updateCheckItemProperty(checkItem);
            }
        }
        return result;
    }

    public Integer delete(CheckItem checkItem) {
        //TODO 需要同步删除t_checkitem_property表
        checkItemTagService.deleteAllCheckItemTagRel(checkItem.getId());
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
