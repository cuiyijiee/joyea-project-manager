package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import me.cuiyijie.joyea.dao.main.CheckItemTagDao;
import me.cuiyijie.joyea.enums.CheckItemTagType;
import me.cuiyijie.joyea.model.CheckItemTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/8 14:19
 */
@Service
public class CheckItemTagService {

    @Autowired
    private CheckItemTagDao checkItemTagDao;

    public CheckItemTag selectById(Integer id) {
        return checkItemTagDao.selectById(id);
    }

    public CheckItemTag selectByTypeAndName(CheckItemTagType tagType, String tagName) {
        return checkItemTagDao.selectByTypeAndName(tagType, tagName);
    }

    public List<CheckItemTag> listByCheckItemId(Integer checkItemId){
        return checkItemTagDao.listByCheckItemId(checkItemId);
    }

    public List<CheckItemTag> list(CheckItemTag checkItemTag) {
        return checkItemTagDao.list(checkItemTag);
    }

    public Integer update(CheckItemTag checkItemTag) {
        return checkItemTagDao.update(checkItemTag);
    }

    public Integer insert(CheckItemTag checkItemTag) {
        return checkItemTagDao.insert(checkItemTag);
    }

    public Integer delete(CheckItemTag checkItemTag) {
        return checkItemTagDao.delete(checkItemTag);
    }

    public Integer getTagRelNum(CheckItemTag checkItemTag) {
        return checkItemTagDao.getTagRelNum(checkItemTag);
    }

    public Integer addCheckItemTagRel(Integer checkItemId,Integer tagId){
        return checkItemTagDao.addCheckItemTagRel(checkItemId,tagId);
    }

    public Integer deleteCheckItemTagRel(Integer checkItemId,Integer tagId){
        return checkItemTagDao.deleteCheckItemTagRel(checkItemId,tagId);
    }

    public Integer deleteAllCheckItemTagRel(Integer checkItemId){
        return checkItemTagDao.deleteAllCheckItemTagRel(checkItemId);
    }

    public Integer selectRelCount(Integer checkItemId,Integer tagId){
        return checkItemTagDao.selectRelCount(checkItemId,tagId);
    }

}
