package me.cuiyijie.joyea.service;

import me.cuiyijie.joyea.domain.CheckItemFormData;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormColumnRequest;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormRequest;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormUpdateAllRequest;

import java.util.List;

public interface ICheckItemFormDataService {

    void updateRowData(TransCheckItemFormRequest request);

    void updateColumnData(TransCheckItemFormColumnRequest request);

    void updateAllRowData(TransCheckItemFormUpdateAllRequest request);

    List<CheckItemFormData> findAll(String checkItemId, Integer rowIndex, Integer columnId, Integer dataType);

    Integer deleteRowData(String checkItemId, Integer dataType, Integer rowIndex);

    void insertOrUpdateCellData(String checkItemId,Integer dataType,Integer columnId,Integer rowIndex,String columnValue);
}
