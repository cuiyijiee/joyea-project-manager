package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.CheckItemAttachmentDao;
import me.cuiyijie.joyea.dao.CheckItemDao;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.CheckItemAttachment;
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

    public Page<CheckItem> list(CheckItem checkItem, Integer pageNum, Integer pageSize) {
        Page<CheckItem> checkItemPage = new Page<>(pageNum, pageSize);
        QueryWrapper<CheckItem> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(checkItem.getTaskId())) {
            queryWrapper.eq("CFTASKID", checkItem.getTaskId());
        }
        if (StringUtils.hasLength(checkItem.getCheckStandard())) {
            queryWrapper.like("CFCHECKSTANDARD", checkItem.getCheckStandard());
        }
        if (checkItem.getKeyItem() != null || checkItem.getKeyItem() != -1) {
            queryWrapper.like("CFKEYITEM", checkItem.getKeyItem());
        }
        queryWrapper.orderByAsc("CFSEQ");

        Page<CheckItem> checkItemPageResult = checkItemDao.selectPage(checkItemPage, queryWrapper);
        for (int index = 0; index < checkItemPageResult.getRecords().size(); index++) {
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
