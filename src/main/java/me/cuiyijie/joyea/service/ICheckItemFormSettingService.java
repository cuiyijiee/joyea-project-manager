package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.CheckItemFormSetting;

import java.util.List;

public interface ICheckItemFormSettingService {

    List<CheckItemFormSetting> listAll(String checkItemId);

}
