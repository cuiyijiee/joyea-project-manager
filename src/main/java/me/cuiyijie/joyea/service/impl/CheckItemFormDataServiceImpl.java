package me.cuiyijie.joyea.service.impl;

import me.cuiyijie.joyea.dao.main.CheckItemFormDataDao;
import me.cuiyijie.joyea.domain.CheckItemFormData;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormColumnRequest;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormRequest;
import me.cuiyijie.joyea.pojo.request.TransCheckItemFormUpdateAllRequest;
import me.cuiyijie.joyea.service.ICheckItemFormDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemFormDataServiceImpl implements ICheckItemFormDataService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CheckItemFormDataServiceImpl.class);

    @Autowired
    private CheckItemFormDataDao checkItemFormDataDao;

    @Override
    public void updateRowData(TransCheckItemFormRequest request) {
        for (int index = 0; index < request.getData().size(); index++) {
            TransCheckItemFormRequest.FormData toUpdateData = request.getData().get(index);

            insertOrUpdateCellData(request.getCheckItem(), request.getDataType(), toUpdateData.getId(),
                    request.getRowIndex(), toUpdateData.getValue());
        }
    }

    @Override
    public void updateColumnData(TransCheckItemFormColumnRequest request) {
        if (request.getData().size() != 0) {
            request.getData().forEach(item -> {
                //更新单元格数据
                insertOrUpdateCellData(request.getCheckItemId(), request.getDataType(), request.getColumnId(),
                        item.getRowIndex(), item.getValue());
            });
        } else {
            LOGGER.info("待更新条数为0，请求无数据需要更新！");
        }
    }

    @Override
    public void updateAllRowData(TransCheckItemFormUpdateAllRequest request) {

        List<TransCheckItemFormUpdateAllRequest.RowData> rowDatas = request.getRowData();
        if (rowDatas != null && rowDatas.size() > 0) {
            for (int index = 0; index < rowDatas.size(); index++) {

                TransCheckItemFormUpdateAllRequest.RowData rowData = rowDatas.get(index);

                TransCheckItemFormRequest transCheckItemFormRequest = new TransCheckItemFormRequest();
                transCheckItemFormRequest.setCheckItem(request.getCheckItemId());
                transCheckItemFormRequest.setDataType(request.getDataType());
                transCheckItemFormRequest.setRowIndex(rowData.getRowIndex());
                transCheckItemFormRequest.setData(rowData.getData());

                updateRowData(transCheckItemFormRequest);
            }
        }
    }

    @Override
    public List<CheckItemFormData> findAll(String checkItemId, Integer rowIndex,Integer columnId, Integer dataType) {

        CheckItemFormData selection = new CheckItemFormData();
        selection.setCheckItemId(checkItemId);
        selection.setRowIndex(rowIndex);
        selection.setColumnId(columnId);
        selection.setDataType(dataType);

        return checkItemFormDataDao.selectAllBy(selection);
    }

    @Override
    public Integer deleteRowData(String checkItemId, Integer dataType, Integer rowIndex) {
        return checkItemFormDataDao.deleteRowData(checkItemId, dataType, rowIndex);
    }

    /**
     * 更新单元格数据
     *
     * @param checkItemId 点检项Id
     * @param dataType    检验类型
     * @param columnId    列Id
     * @param rowIndex    行Index
     * @param columnValue 单元格内容
     */
    @Override
    public void insertOrUpdateCellData(String checkItemId, Integer dataType, Integer columnId, Integer rowIndex, String columnValue) {
        CheckItemFormData selection = new CheckItemFormData();

        selection.setRowIndex(rowIndex);
        selection.setCheckItemId(checkItemId);
        selection.setColumnId(columnId);
        selection.setDataType(dataType);
        CheckItemFormData checkItemFormData = checkItemFormDataDao.selectBy(selection);

        if (checkItemFormData == null) {
            //新增操作
            CheckItemFormData insertion = new CheckItemFormData();
            insertion.setCheckItemId(checkItemId);
            insertion.setRowIndex(rowIndex);
            insertion.setColumnId(columnId);
            insertion.setDataType(dataType);
            insertion.setColumnValue(columnValue);
            checkItemFormDataDao.insert(insertion);
        } else {
            //更新操作
            CheckItemFormData updation = new CheckItemFormData();
            updation.setCheckItemId(checkItemId);
            updation.setRowIndex(rowIndex);
            updation.setColumnId(columnId);
            updation.setDataType(dataType);
            updation.setColumnValue(columnValue);
            checkItemFormDataDao.update(updation);
        }
    }
}
