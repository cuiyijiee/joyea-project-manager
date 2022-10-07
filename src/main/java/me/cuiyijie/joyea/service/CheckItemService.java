package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.CheckItemDao;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    public Page<CheckItem> list(CheckItem checkItem, Integer pageNum, Integer pageSize) {
        Page<CheckItem> checkItemPage = new Page<>(pageNum, pageSize);
        QueryWrapper<CheckItem> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper.eq("CFTASKID", checkItem.getTaskId());
        }
        queryWrapper.orderByAsc("CFSEQ");
        return checkItemDao.selectPage(checkItemPage, queryWrapper);
    }

    public List<CheckItem> listChild(Integer id) {
        List<CheckItem> checkItems = checkItemDao.listChild(id);
        return checkItems;
    }

    public List<CheckItem> listAll(CheckItemVo checkItemVo) {
        List<CheckItem> checkItems = checkItemDao.listAll(checkItemVo);
        return checkItems;
    }

    public Integer update(CheckItem checkItem) {
        return checkItemDao.update(checkItem);
    }

    @Transactional
    public Integer insert(CheckItem checkItem) {
        Integer result = checkItemDao.insert(checkItem);
        return result;
    }

    public Integer delete(CheckItem checkItem) {
        return checkItemDao.delete(checkItem);
    }

    public Integer updateState(@Param("item") CheckItem checkItem) {
        return checkItemDao.updateState(checkItem);
    }
}
