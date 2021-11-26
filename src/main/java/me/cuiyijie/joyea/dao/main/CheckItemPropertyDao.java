package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.CheckItemProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/26 14:13
 */
@Repository
public interface CheckItemPropertyDao {


    List<CheckItemProperty> list(@Param("item") CheckItemProperty checkItemProperty);

    Integer insert(@Param("item") CheckItemProperty checkItemProperty);

    Integer deleteByCheckItemIdAndType(@Param("item") CheckItemProperty checkItemProperty);


}
