package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.dao.CheckItemResultAttachmentDao;
import me.cuiyijie.joyea.dao.CheckItemResultDao;
import me.cuiyijie.joyea.model.CheckItemResult;
import me.cuiyijie.joyea.model.CheckItemResultAttachment;
import me.cuiyijie.joyea.model.EasUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckItemResultService extends ServiceImpl<CheckItemResultDao, CheckItemResult> {

    private final CheckItemResultAttachmentDao checkItemResultAttachmentDao;
    private final EasUserService userService;

    public IPage<CheckItemResult> list(CheckItemResult checkItemResult, Integer pageNum, Integer pageSize) {
        Page<CheckItemResult> checkItemResultPage = new Page<>(pageNum, pageSize);

        IPage<CheckItemResult> checkItemResultIPage = baseMapper
                .selectWithPage(checkItemResultPage, checkItemResult);

        checkItemResultIPage.getRecords().forEach(record -> {
            List<CheckItemResultAttachment> attachmentList = checkItemResultAttachmentDao.selectList(new QueryWrapper<CheckItemResultAttachment>().eq("CHECKRESULTID", record.getFId()));
            record.setAttachmentList(attachmentList);
        });

        return checkItemResultIPage;
    }


    @Transactional
    public void insert(String easUserId, CheckItemResult checkItemResult) {

        checkItemResult.setCfCheckDate(new Date());
        checkItemResult.setFCreatorId(easUserId);

        EasUser easUser = userService.findById(easUserId);
        if (easUser != null) {
            checkItemResult.setCfCheckPersonId(easUser.getFPersonId());
        }else {
            checkItemResult.setCfCheckPersonId("test");
        }
        baseMapper.customInsert(checkItemResult);
        String fid = checkItemResult.getFId();

        for (CheckItemResultAttachment checkItemResultAttachment : checkItemResult.getAttachmentList()) {
            checkItemResultAttachment.setUpdateTime(new Date());
            checkItemResultAttachment.setCheckResultId(fid);

            checkItemResultAttachmentDao.insert(checkItemResultAttachment);
        }
    }

    public List<CheckItemResult> findRecentResult(String checkEntryId) {
        QueryWrapper<CheckItemResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CFCHECKENTRYID", checkEntryId);
        queryWrapper.orderByDesc("CFCHECKDATE");
        return baseMapper.selectList(queryWrapper);
    }

}
