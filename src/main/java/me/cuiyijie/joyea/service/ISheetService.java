package me.cuiyijie.joyea.service;


import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.model.Sheet;

public interface ISheetService {

    PageInfo<Sheet> findAll(Sheet sheet, Integer pageNum, Integer pageSize);

    Integer insert(Sheet sheet);

    Integer update(Sheet sheet);

    Integer delete(Sheet sheet);

}
