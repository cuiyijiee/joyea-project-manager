package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.Template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemDao {

    List<CheckItem> listAll(@Param("item") CheckItem checkItem);

    Integer insert(@Param("item") CheckItem checkItem);

    Integer update(@Param("item") CheckItem checkItem);

    Integer delete(@Param("item") CheckItem checkItem);

}
