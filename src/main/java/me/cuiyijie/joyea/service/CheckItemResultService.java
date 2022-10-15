package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.toolkit.MPJWrappers;
import me.cuiyijie.joyea.dao.CheckItemResultAttachmentDao;
import me.cuiyijie.joyea.dao.CheckItemResultDao;
import me.cuiyijie.joyea.model.CheckItemResult;
import me.cuiyijie.joyea.model.CheckItemResultAttachment;
import me.cuiyijie.joyea.model.Person;
import me.cuiyijie.joyea.model.vo.CheckItemResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckItemResultService {

    @Autowired
    private CheckItemResultDao checkItemResultDao;

    @Autowired
    private CheckItemResultAttachmentDao checkItemResultAttachmentDao;

    public IPage<CheckItemResult> list(CheckItemResult checkItemResult, Integer pageNum, Integer pageSize) {
        Page<CheckItemResult> checkItemResultPage = new Page<>(pageNum, pageSize);

        return checkItemResultDao.selectJoinPage(checkItemResultPage, CheckItemResult.class,
                MPJWrappers.<CheckItemResult>lambdaJoin()
                        .selectAll(CheckItemResult.class)
                        .selectAs(Person::getFName, CheckItemResult::getCfCheckPersonName)
                        .leftJoin(Person.class, Person::getFid, CheckItemResult::getCfCheckPersonId)
                        .eq(CheckItemResult::getCfCheckEntryId, checkItemResult.getCfCheckEntryId())
        );
//        QueryWrapper<CheckItemResult> checkItemResultQueryWrapper = new QueryWrapper<>();
//        if (StringUtils.hasLength(checkItemResult.getCheckEntryId())) {
//            checkItemResultQueryWrapper.eq("CFCHECKENTRYID", checkItemResult.getCheckEntryId());
//        }
//        return checkItemResultDao.selectPage(checkItemResultPage, checkItemResultQueryWrapper);
    }


    public void insert(String personId, CheckItemResult checkItemResult) {

        checkItemResult.setCfCheckDate(new Date());
        checkItemResult.setCfCheckPersonId(personId);

        checkItemResultDao.insert(checkItemResult);
        String fid = checkItemResult.getFId();

        for (CheckItemResultAttachment checkItemResultAttachment : checkItemResult.getAttachmentList()) {
            checkItemResultAttachment.setUpdateTime(new Date());
            checkItemResultAttachment.setCheckResultId(fid);

            checkItemResultAttachmentDao.insert(checkItemResultAttachment);
        }
    }

}
