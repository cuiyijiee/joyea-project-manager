package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.CheckItemResultDao;
import me.cuiyijie.joyea.model.CheckItemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CheckItemResultService {

    @Autowired
    private CheckItemResultDao checkItemResultDao;

    public Page<CheckItemResult> list(CheckItemResult checkItemResult, Integer pageNum, Integer pageSize) {
        Page<CheckItemResult> checkItemResultPage = new Page<>(pageNum, pageSize);

        QueryWrapper<CheckItemResult> checkItemResultQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItemResult.getCheckEntryId())) {
            checkItemResultQueryWrapper.eq("CFCHECKENTRYID", checkItemResult.getCheckEntryId());
        }

        return checkItemResultDao.selectPage(checkItemResultPage, checkItemResultQueryWrapper);
    }

}
