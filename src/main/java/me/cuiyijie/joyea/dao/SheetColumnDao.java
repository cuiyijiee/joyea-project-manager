package me.cuiyijie.joyea.dao;

import me.cuiyijie.joyea.model.SheetColumn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/22 15:13
 */
@Repository
public interface SheetColumnDao {

    Integer insert(@Param("item") SheetColumn sheetColumn);

    Integer update(@Param("item") SheetColumn sheetColumn);

    List<SheetColumn> findAll(@Param("item") SheetColumn sheetColumn);

    SheetColumn find(@Param("item") SheetColumn sheetColumn);

    Integer delete(@Param("item") SheetColumn sheetColumn);

    Integer deleteSheetColumn(@Param("sheetId") Integer sheetId);

}
