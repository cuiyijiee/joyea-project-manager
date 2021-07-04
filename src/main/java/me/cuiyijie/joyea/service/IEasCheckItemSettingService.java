package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.EasCheckItemSetting;

public interface IEasCheckItemSettingService {

    EasCheckItemSetting select(String operationId, String operationName);

    Integer insert(EasCheckItemSetting easCheckItemSetting);

    Integer update(EasCheckItemSetting easCheckItemSetting);

}
