package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.SearchHistoryDao;
import me.cuiyijie.joyea.model.SearchHistory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchHistoryService extends ServiceImpl<SearchHistoryDao, SearchHistory> {

    public void addSearchHistory(String easUserId, String searchType, String searchKey) {
        SearchHistory exiatedSearchHistory = baseMapper.selectHistory(easUserId, searchType, searchKey);
        if (exiatedSearchHistory == null) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setEasUserId(easUserId);
            searchHistory.setSearchType(searchType);
            searchHistory.setSearchKey(searchKey);
            searchHistory.setUpdateTime(new Date());
            baseMapper.insert(searchHistory);
        } else {
            exiatedSearchHistory.setUpdateTime(new Date());
            baseMapper.updateById(exiatedSearchHistory);
        }
    }

    public List<SearchHistory> getLastHistory(String easUserId, String searchType, Integer count) {
        Page<SearchHistory> page = new Page<>();
        page.setCurrent(0);
        page.setSize(count);

        QueryWrapper<SearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EAS_USER_ID", easUserId);
        queryWrapper.eq("SEARCH_TYPE", searchType);
        queryWrapper.orderByDesc("UPDATE_TIME");
        return baseMapper.selectPage(page,queryWrapper).getRecords();
    }
}
