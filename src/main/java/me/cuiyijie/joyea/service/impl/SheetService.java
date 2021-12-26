package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import me.cuiyijie.joyea.dao.main.SheetColumnDao;
import me.cuiyijie.joyea.dao.main.SheetDao;
import me.cuiyijie.joyea.dao.main.SheetDataDao;
import me.cuiyijie.joyea.model.Sheet;
import me.cuiyijie.joyea.model.SheetColumn;
import me.cuiyijie.joyea.model.SheetData;
import me.cuiyijie.joyea.model.vo.SheetColumnVo;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheetService {

    @Autowired
    private SheetDao sheetDao;

    @Autowired
    private SheetColumnDao sheetColumnDao;

    @Autowired
    private SheetDataDao sheetDataDao;

    public PageInfo<Sheet> findAll(Sheet sheet, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sheet> sheets = sheetDao.findAll(sheet);
        return new PageInfo<>(sheets);
    }

    public Integer insert(Sheet sheet) {
        return sheetDao.insert(sheet);
    }

    public Integer update(Sheet sheet) {
        return sheetDao.update(sheet);
    }

    public Integer delete(Sheet sheet) {
        return sheetDao.delete(sheet);
    }

    public List<String> listAllCategory() {
        return sheetDao.listAllCategory();
    }

    public List<SheetColumn> listAllColumns(Integer sheetId) {
        SheetColumn sheetColumn = new SheetColumn();
        sheetColumn.setSheetId(sheetId);
        return sheetColumnDao.findAll(sheetColumn);
    }


    @Transactional
    public void updateAllColumns(Integer sheetId, List<SheetColumn> sheetColumns) {
        for (int index = 0; index < sheetColumns.size(); index++) {
            SheetColumn sheetColumn = sheetColumns.get(index);
            sheetColumn.setSheetId(sheetId);

            List<String> paramsCheck = Lists.newArrayList(
                    "sheetId:表格ID（sheetId）",
                    "columnIndex:第几列（columnIndex）",
                    "columnName:列名（columnName）",
                    "columnType:列的类型（columnType）"
            );
            String errorMsg = CheckParamsUtil.checkAll(sheetColumn, paramsCheck, null, null);
            if (StringUtils.hasLength(errorMsg)) {
                throw new RuntimeException("参数检查错误：" + errorMsg);
            }

            sheetColumnDao.deleteSheetColumn(sheetId);
            sheetColumnDao.insert(sheetColumn);

//            SheetColumn existSheetColumn = sheetColumnDao.find(sheetColumn);
//            if (existSheetColumn == null) {
//                sheetColumnDao.insert(sheetColumn);
//            } else {
//                sheetColumnDao.update(sheetColumn);
//            }
        }
    }


    public List<SheetColumnVo> listAllData(SheetData sheetData) {
        List<SheetColumnVo> result = new ArrayList<>();

        SheetColumn sheetColumn = new SheetColumn();
        sheetColumn.setSheetId(sheetData.getSheetId());
        List<SheetColumn> columns = sheetColumnDao.findAll(sheetColumn);

        for (int index = 0; index < columns.size(); index++) {
            SheetColumn sheetColumn1 = columns.get(index);
            SheetData sheetData1 = new SheetData();
            sheetData1.setSheetId(sheetData.getSheetId());
            sheetData1.setColumnId(sheetColumn1.getId());
            sheetData1.setStageRelId(sheetData.getStageRelId());
            List<SheetData> sheetDataList = sheetDataDao.list(sheetData1);

            SheetColumnVo sheetColumnVo = new SheetColumnVo();
            BeanUtils.copyProperties(sheetColumn1, sheetColumnVo);
            sheetColumnVo.setDataList(sheetDataList);
            result.add(sheetColumnVo);
        }

        return result;
    }


    @Transactional
    public void updateAllData(Integer sheetId, Integer stageRelId, List<SheetData> sheetDataList) {

        for (int index = 0; index < sheetDataList.size(); index++) {
            SheetData sheetData = sheetDataList.get(index);
            sheetData.setSheetId(sheetId);
            sheetData.setStageRelId(stageRelId);

            List<String> paramsCheck = Lists.newArrayList(
                    "stageRelId:阶段ID（stageRelId）",
                    "sheetId:表格ID（sheetId）",
                    "columnId:列ID（columnId）",
                    "rowIndex:行数（rowIndex）"
            );
            String errorMsg = CheckParamsUtil.checkAll(sheetData, paramsCheck, null, null);
            if (StringUtils.hasLength(errorMsg)) {
                throw new RuntimeException("参数检查错误：" + errorMsg);
            }
            SheetData sheetData1 = sheetDataDao.find(sheetData);
            if (sheetData1 == null) {
                sheetDataDao.insert(sheetData);
            } else {
                sheetData1.setColumnValue(sheetData.getColumnValue());
                sheetDataDao.update(sheetData1);
            }

        }

    }
}
