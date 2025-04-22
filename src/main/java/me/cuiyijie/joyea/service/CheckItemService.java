package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.CheckItemAttachmentDao;
import me.cuiyijie.joyea.dao.CheckItemDao;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemAttachment;
import me.cuiyijie.joyea.model.CheckItemResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CheckItemService  extends ServiceImpl<CheckItemDao, CheckItem> {

    private final CheckItemAttachmentDao checkItemAttachmentDao;
    private final CheckItemResultService checkItemResultService;
    private final SearchHistoryService searchHistoryService;

    public IPage<CheckItem> list(String easUserId, CheckItem checkItem, Integer pageNum, Integer pageSize) {

        if(StringUtils.hasLength(checkItem.getCheckStandard())) {
            searchHistoryService.addSearchHistory(easUserId, "CACHE_CHECKITEM_SEARCH_HISTORY" , checkItem.getCheckStandard());
        }

        IPage<CheckItem> checkItemPageResult = baseMapper.selectWithPage(new Page<>(pageNum, pageSize), checkItem);

        for (int index = 0; index < checkItemPageResult.getRecords().size(); index++) {
            //查找附件
            CheckItem checkItem1 = checkItemPageResult.getRecords().get(index);
            String checkModeId = checkItem1.getCheckModeId();
            if (StringUtils.hasLength(checkModeId)) {
                checkItem1.setAttachmentList(checkItemAttachmentDao.selectList(new QueryWrapper<CheckItemAttachment>().eq("FID", checkModeId)));
            }


            //设置是否合格
            List<CheckItemResult> checkItemResults = checkItemResultService.findRecentResult(checkItem1.getFid());

            List<CheckItemResult> myCheckResults = checkItemResults.stream().filter(item -> {
                return item.getCfCheckType().equals(checkItem.getCfCheckType());
            }).collect(Collectors.toList());
            //无点检记录
            if (myCheckResults.size() == 0) {
                checkItem1.setCfCheckResult("");
            } else {
                checkItem1.setCfCheckResult(myCheckResults.get(0).getCfCheckResult());
            }
        }
        return checkItemPageResult;
    }

    public CheckItem find(CheckItem checkItem) {
        CheckItem checkItem1 = baseMapper.selectById(checkItem.getFid());
        if (checkItem1 != null) {
            String checkModeId = checkItem1.getCheckModeId();
            if (StringUtils.hasLength(checkModeId)) {
                checkItem1.setAttachmentList(checkItemAttachmentDao.selectList(new QueryWrapper<CheckItemAttachment>().eq("FID", checkModeId)));
            }
        }
        return checkItem1;
    }

    public String listCount(CheckItem checkItem) {

        //自检和第三方
        QueryWrapper<CheckItem> queryWrapper1 = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper1.eq("CFTASKID", checkItem.getTaskId());
        }
        queryWrapper1.eq("CFKEYITEM", 0);
        Long count1 = baseMapper.selectCount(queryWrapper1);

        //互检
        QueryWrapper<CheckItem> queryWrapper2 = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper2.eq("CFTASKID", checkItem.getTaskId());
        }
        queryWrapper2.eq("CFKEYITEM", 1);
        Long count2 = baseMapper.selectCount(queryWrapper2);

        //第三方专检
        QueryWrapper<CheckItem> queryWrapper3 = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper3.eq("CFTASKID", checkItem.getTaskId());
        }
        queryWrapper3.apply("SUBSTR(CFCHECKSTANDARD, 1, 3)  = '★★★'");
        Long count3 = baseMapper.selectCount(queryWrapper3);
        return String.format("%s_%s_%s", count1, count2, count3);
    }
}
