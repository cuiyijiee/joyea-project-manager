package me.cuiyijie.joyea.controller;

import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.model.SearchHistory;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("searchHistory")
public class SearchHistoryController {


    @Autowired
    private SearchHistoryService searchHistoryService;

    @PostMapping("latest")
    private TransBaseResponse getLatestSearchHistory(@RequestBody SearchHistory searchHistory, @CurrentUser CurrentUserInfo currentUserInfo) {
        List<SearchHistory> latestHistory = searchHistoryService.getLastHistory(currentUserInfo.getEasUserId(), searchHistory.getSearchType(), 10);
        return TransBaseResponse.success(latestHistory);
    }

}
