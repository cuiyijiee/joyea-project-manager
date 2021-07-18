package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.CheckItemFormData;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormRequest;

import java.util.List;

public interface ICheckItemFormDataService {

    void updateAllFormData(TransCheckItemFormRequest request);

    List<CheckItemFormData> findAll(String checkItemId, Integer rowIndex,Integer dataType);
}
