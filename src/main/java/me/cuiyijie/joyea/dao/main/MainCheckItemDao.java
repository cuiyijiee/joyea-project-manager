package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainCheckItemDao {

    Integer insert(@Param("checkItem") JoyeaCheckItem joyeaCheckItem);

    Integer insertMany(@Param("list") List<JoyeaCheckItem> list);

    Integer update(@Param("checkItem") JoyeaCheckItem joyeaCheckItem);

    List<JoyeaCheckItem> list(@Param("checkItem")JoyeaCheckItem joyeaCheckItem);

    JoyeaCheckItem findById(@Param("id") String id);

    Integer delete(@Param("id")String id);

}
