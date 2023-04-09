package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.joyea.dao.SearchHistoryDao;
import me.cuiyijie.joyea.model.SearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    private SearchHistoryDao searchHistoryDao;


    private void addSearchHistory(String easUserId, String searchType, String searchKey) {
        SearchHistory exiatedSearchHistory = searchHistoryDao.selectHistory(easUserId, searchType, searchKey);
        if (exiatedSearchHistory == null) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setEasUserId(easUserId);
            searchHistory.setSearchType(searchType);
            searchHistory.setSearchKey(searchKey);
            searchHistory.setUpdateTime(new Date());
            searchHistoryDao.insert(searchHistory);
        } else {
            exiatedSearchHistory.setUpdateTime(new Date());
            searchHistoryDao.updateById(exiatedSearchHistory);
        }
    }

    private List<SearchHistory> getLastHistory(String easUserId, String searchType, Integer count) {
        QueryWrapper<SearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EAS_USER_ID", easUserId);
        queryWrapper.eq("SEARCH_TYPE", searchType);
        return new ArrayList<>();
    }
}
