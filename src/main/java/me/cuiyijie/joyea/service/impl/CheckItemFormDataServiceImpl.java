package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.CheckItemFormDataDao;
import me.cuiyijie.joyea.domain.CheckItemFormData;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormRequest;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormUpdateAllRequest;
import me.cuiyijie.joyea.service.ICheckItemFormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemFormDataServiceImpl implements ICheckItemFormDataService {

    @Autowired
    private CheckItemFormDataDao checkItemFormDataDao;

    @Override
    public void updateRowData(TransCheckItemFormRequest request) {
        for (int index = 0; index < request.getData().size(); index++) {
            TransCheckItemFormRequest.FormData toUpdateData = request.getData().get(index);
            CheckItemFormData selection = new CheckItemFormData();

            selection.setRowIndex(request.getRowIndex());
            selection.setCheckItemId(request.getCheckItem());
            selection.setColumnId(toUpdateData.getId());
            selection.setDataType(request.getDataType());
            CheckItemFormData checkItemFormData = checkItemFormDataDao.selectBy(selection);

            if (checkItemFormData == null) {
                //新增操作
                CheckItemFormData insertion = new CheckItemFormData();
                insertion.setCheckItemId(request.getCheckItem());
                insertion.setRowIndex(request.getRowIndex());
                insertion.setColumnId(toUpdateData.getId());
                insertion.setColumnValue(toUpdateData.getValue());
                insertion.setDataType(request.getDataType());

                checkItemFormDataDao.insert(insertion);
            } else {
                //更新操作
                CheckItemFormData updation = new CheckItemFormData();
                updation.setCheckItemId(request.getCheckItem());
                updation.setRowIndex(request.getRowIndex());
                updation.setColumnId(toUpdateData.getId());
                updation.setColumnValue(toUpdateData.getValue());
                updation.setDataType(request.getDataType());

                checkItemFormDataDao.update(updation);
            }
        }
    }

    @Override
    public void updateAllRowData(TransCheckItemFormUpdateAllRequest request) {

        List<TransCheckItemFormUpdateAllRequest.RowData> rowDatas = request.getRowData();
        if (rowDatas != null && rowDatas.size() > 0) {
            for (int index = 0; index < rowDatas.size(); index++) {

                TransCheckItemFormUpdateAllRequest.RowData rowData = rowDatas.get(index);

                TransCheckItemFormRequest transCheckItemFormRequest = new TransCheckItemFormRequest();
                transCheckItemFormRequest.setCheckItem(request.getCheckItem());
                transCheckItemFormRequest.setDataType(request.getDataType());
                transCheckItemFormRequest.setRowIndex(rowData.getRowIndex());
                transCheckItemFormRequest.setData(rowData.getData());

                updateRowData(transCheckItemFormRequest);
            }
        }
    }

    @Override
    public List<CheckItemFormData> findAll(String checkItemId, Integer rowIndex, Integer dataType) {

        CheckItemFormData selection = new CheckItemFormData();
        selection.setCheckItemId(checkItemId);
        selection.setRowIndex(rowIndex);
        selection.setDataType(dataType);

        return checkItemFormDataDao.selectAllBy(selection);
    }

    @Override
    public Integer deleteRowData(String checkItemId, Integer dataType, Integer rowIndex) {
        return checkItemFormDataDao.deleteRowData(checkItemId,dataType,rowIndex);
    }
}
