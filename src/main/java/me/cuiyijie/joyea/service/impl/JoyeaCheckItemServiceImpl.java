package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.joyea.JoyeaCheckItemDao;
import me.cuiyijie.joyea.dao.main.MainCheckItemDao;
import me.cuiyijie.joyea.domain.EasCheckItemSetting;
import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import me.cuiyijie.joyea.pojo.request.TransCheckItemRequest;
import me.cuiyijie.joyea.service.IEasCheckItemSettingService;
import me.cuiyijie.joyea.service.IJoyeaCheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoyeaCheckItemServiceImpl implements IJoyeaCheckItemService {

    @Autowired
    private JoyeaCheckItemDao joyeaCheckItemDao;

    @Autowired
    private MainCheckItemDao mainCheckItemDao;

    @Autowired
    private IEasCheckItemSettingService easCheckItemSettingService;

    @Override
    public PageInfo<JoyeaCheckItem> findByOperationIdAndNo(TransCheckItemRequest transCheckItemRequest) {

        EasCheckItemSetting easCheckItemSetting = easCheckItemSettingService.select(transCheckItemRequest.getOperationId(), transCheckItemRequest.getOperationNo());
        if (easCheckItemSetting == null || !easCheckItemSetting.isSyncCheckItem()) {

            List<JoyeaCheckItem> originCheckItems = joyeaCheckItemDao.selectByOperationIdAndOperationNo(transCheckItemRequest.getOperationId(), transCheckItemRequest.getOperationNo());
            //mainCheckItemDao.insertMany(originCheckItems);

            originCheckItems.forEach(item -> {
                List<JoyeaCheckItem> existedItem = mainCheckItemDao.list(item);
                if(existedItem.size() <= 0){
                    mainCheckItemDao.insert(item);
                }
            });

            EasCheckItemSetting insertOrUpdate = new EasCheckItemSetting();
            insertOrUpdate.setOperationId(transCheckItemRequest.getOperationId());
            insertOrUpdate.setOperationNo(transCheckItemRequest.getOperationNo());
            insertOrUpdate.setSyncCheckItem(true);

            if (easCheckItemSetting == null) {
                easCheckItemSettingService.insert(insertOrUpdate);
            }else{
                easCheckItemSettingService.update(insertOrUpdate);
            }

        }

        JoyeaCheckItem selectParams = new JoyeaCheckItem();
        selectParams.setOperationId(transCheckItemRequest.getOperationId());
        selectParams.setOperationNo(transCheckItemRequest.getOperationNo());

        PageHelper.startPage(transCheckItemRequest.getPageNum(), transCheckItemRequest.getPageSize());

        List<JoyeaCheckItem> resultCheckItems = mainCheckItemDao.list(selectParams);
        return new PageInfo<>(resultCheckItems);
    }

    @Override
    public Integer add(JoyeaCheckItem joyeaCheckItem) {
        return mainCheckItemDao.insert(joyeaCheckItem);
    }

    @Override
    public Integer update(JoyeaCheckItem joyeaCheckItem) {
        return mainCheckItemDao.update(joyeaCheckItem);
    }

    @Override
    public Integer delete(JoyeaCheckItem joyeaCheckItem) {
        return mainCheckItemDao.delete(joyeaCheckItem.getId());
    }
}
