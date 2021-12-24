package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.SheetData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/22 16:14
 */
@Repository
public interface SheetDataDao {


    Integer insert(@Param("item") SheetData sheetData);

    Integer update(@Param("item") SheetData sheetData);

    Integer delete(@Param("item") SheetData sheetData);

    List<SheetData> list(@Param("item") SheetData sheetData);

    SheetData find(@Param("item") SheetData sheetData);

}
