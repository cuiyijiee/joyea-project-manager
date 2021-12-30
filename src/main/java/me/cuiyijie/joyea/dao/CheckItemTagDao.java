package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.cuiyijie.joyea.enums.CheckItemTagType;
import me.cuiyijie.joyea.model.CheckItemTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/11/8 10:43
 */
@Repository
public interface CheckItemTagDao {

    CheckItemTag selectById(@Param("id")Integer id);

    int insert(@Param("item") CheckItemTag entity);

    List<CheckItemTag> list(@Param("item") CheckItemTag checkItemTag);

    Integer update(@Param("item") CheckItemTag checkItemTag);

    Integer delete(@Param("item") CheckItemTag checkItemTag);

    Integer getTagRelNum(@Param("item") CheckItemTag checkItemTag);

    CheckItemTag selectByTypeAndName(@Param("tagType") CheckItemTagType tagType, @Param("tagName") String tagName);

    List<CheckItemTag> listByCheckItemId(@Param("checkItemId") Integer checkItemId);

    Integer addCheckItemTagRel(@Param("checkItemId") Integer checkItemId, @Param("tagId") Integer tagId);

    Integer deleteCheckItemTagRel(@Param("checkItemId") Integer checkItemId, @Param("tagId") Integer tagId);

    Integer deleteAllCheckItemTagRel(@Param("checkItemId") Integer checkItemId);

    Integer selectRelCount(@Param("checkItemId") Integer checkItemId, @Param("tagId") Integer tagId);
}
