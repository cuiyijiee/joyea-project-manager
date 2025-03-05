package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.CheckItemResultAttachmentDao;
import me.cuiyijie.joyea.model.CheckItemResultAttachment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckItemResultAttachmentService extends ServiceImpl<CheckItemResultAttachmentDao, CheckItemResultAttachment> {

    public Page<CheckItemResultAttachment> findLenovoCheckItemResultAttachment(Page<CheckItemResultAttachment> page) {
        LambdaQueryWrapper<CheckItemResultAttachment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(CheckItemResultAttachment::getMimeType, "edoc2");
        queryWrapper.orderByDesc(CheckItemResultAttachment::getUpdateTime);
        return page(page, queryWrapper);
    }

}
