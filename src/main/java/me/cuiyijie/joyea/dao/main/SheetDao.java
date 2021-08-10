package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.Sheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetDao {

    List<Sheet> findAll(@Param("item") Sheet sheet);

    Integer insert(@Param("item") Sheet sheet);

    Integer update(@Param("item") Sheet sheet);

    Integer delete(@Param("item") Sheet sheet);

}
