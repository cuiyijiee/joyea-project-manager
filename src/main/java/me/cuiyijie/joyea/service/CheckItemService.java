package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.CheckItemAttachmentDao;
import me.cuiyijie.joyea.dao.CheckItemDao;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemAttachment;
import me.cuiyijie.joyea.model.CheckItemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Autowired
    private CheckItemAttachmentDao checkItemAttachmentDao;
    @Autowired
    private CheckItemResultService checkItemResultService;

    public IPage<CheckItem> list(CheckItem checkItem, Integer pageNum, Integer pageSize) {

        IPage<CheckItem> checkItemPageResult = checkItemDao.selectWithPage(new Page<>(pageNum, pageSize), checkItem);

        for (int index = 0; index < checkItemPageResult.getRecords().size(); index++) {
            //查找附件
            CheckItem checkItem1 = checkItemPageResult.getRecords().get(index);
            String ffid = checkItem1.getCheckMethodId();
            List<CheckItemAttachment> attachmentList = new ArrayList<>();
            if (StringUtils.hasLength(ffid)) {
                String[] ffids = ffid.split(",");
                for (int i = 0; i < ffids.length; i++) {
                    String attachId = ffids[i];
                    CheckItemAttachment checkItemAttachment = checkItemAttachmentDao.selectById(attachId);
                    if (checkItemAttachment != null) {
                        attachmentList.add(checkItemAttachment);
                    }
                }
            }
            checkItem1.setAttachmentList(attachmentList);

            //设置是否合格
            List<CheckItemResult> checkItemResults = checkItemResultService.findRecentResult(checkItem1.getFid());
            if (checkItemResults != null && checkItemResults.size() > 0
                    && ("1".equals(checkItemResults.get(0).getCfCheckResult()) || "3".equals(checkItemResults.get(0).getCfCheckResult()))) {
                checkItem1.setQualified(true);
            } else {
                checkItem1.setQualified(false);
            }
        }
        return checkItemPageResult;
    }

    public CheckItem find(CheckItem checkItem) {
        CheckItem checkItem1 = checkItemDao.selectById(checkItem.getFid());
        if (checkItem1 != null) {
            String ffid = checkItem1.getCheckMethodId();
            List<CheckItemAttachment> attachmentList = new ArrayList<>();
            if (StringUtils.hasLength(ffid)) {
                String[] ffids = ffid.split(",");
                for (int i = 0; i < ffids.length; i++) {
                    String attachId = ffids[i];
                    CheckItemAttachment checkItemAttachment = checkItemAttachmentDao.selectById(attachId);
                    if (checkItemAttachment != null) {
                        attachmentList.add(checkItemAttachment);
                    }
                }
            }
            checkItem1.setAttachmentList(attachmentList);
        }
        return checkItem1;
    }

    public String listCount(CheckItem checkItem) {

        QueryWrapper<CheckItem> queryWrapper1 = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper1.eq("CFTASKID", checkItem.getTaskId());
        }
        queryWrapper1.eq("CFKEYITEM", 0);
        Long count1 = checkItemDao.selectCount(queryWrapper1);

        QueryWrapper<CheckItem> queryWrapper2 = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper2.eq("CFTASKID", checkItem.getTaskId());
        }
        queryWrapper2.eq("CFKEYITEM", 1);
        Long count2 = checkItemDao.selectCount(queryWrapper2);

        return String.format("%s_%s", count1, count2);
    }
}
