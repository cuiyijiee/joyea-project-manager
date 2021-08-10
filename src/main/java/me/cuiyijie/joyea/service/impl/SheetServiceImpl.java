package me.cuiyijie.joyea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.dao.main.SheetDao;
import me.cuiyijie.joyea.domain.Sheet;
import me.cuiyijie.joyea.service.ISheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheetServiceImpl implements ISheetService {

    @Autowired
    private SheetDao sheetDao;

    @Override
    public PageInfo<Sheet> findAll(Sheet sheet, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sheet> sheets = sheetDao.findAll(sheet);
        return new PageInfo<>(sheets);
    }

    @Override
    public Integer insert(Sheet sheet) {
        return sheetDao.insert(sheet);
    }

    @Override
    public Integer update(Sheet sheet) {
        return sheetDao.update(sheet);
    }

    @Override
    public Integer delete(Sheet sheet) {
        return sheetDao.delete(sheet);
    }
}
