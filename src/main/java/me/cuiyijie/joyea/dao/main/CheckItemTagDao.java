package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.CheckItemTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/11/8 10:43
 */
@Repository
public interface CheckItemTagDao {

    List<CheckItemTag> listAll(@Param("item")CheckItemTag checkItemTag);

    Integer insert(@Param("item") CheckItemTag checkItemTag);

    Integer update(@Param("item") CheckItemTag checkItemTag);

    Integer delete(@Param("item") CheckItemTag checkItemTag);

}
