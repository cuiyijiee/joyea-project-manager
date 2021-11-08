package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.dao.main.CheckItemDao;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Autowired
    private CheckItemTagService checkItemTagService;

    public List<CheckItem> listAll(CheckItem checkItem) {
        List<CheckItem> checkItems = checkItemDao.listAll(checkItem);
        for(int index = 0;index < checkItems.size();index ++) {
            CheckItem checkItem1 = checkItems.get(index);
            CheckItemTag checkItemTag = new CheckItemTag();
            checkItemTag.setCheckItemId(checkItem1.getId());
            List<CheckItemTag> checkItemTags = checkItemTagService.listAll(checkItemTag);
            checkItem1.setTags(checkItemTags);
        }
        return checkItems;
    }

    public Integer update(CheckItem checkItem) {
        return checkItemDao.update(checkItem);
    }

    public Integer insert(CheckItem checkItem) {
        return checkItemDao.insert(checkItem);
    }

    public Integer delete(CheckItem checkItem) {
        return checkItemDao.delete(checkItem);
    }

}
