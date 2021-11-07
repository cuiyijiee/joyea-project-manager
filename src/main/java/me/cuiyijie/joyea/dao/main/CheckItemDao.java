package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.Template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemDao {

    List<CheckItem> listAll();

    Integer insert(@Param("item") CheckItem template);

    Integer update(@Param("item") CheckItem template);

    Integer delete(@Param("item") CheckItem template);

}
