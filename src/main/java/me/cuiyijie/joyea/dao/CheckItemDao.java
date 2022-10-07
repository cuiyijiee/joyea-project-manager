package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemDao extends BaseMapper<CheckItem> {

    List<CheckItem> listAll(@Param("item") CheckItemVo checkItemVo);

    CheckItem listById(@Param("id") Integer id);

    Integer selectCheckItemRel(@Param("checkItemId")Integer checkItemId);

    List<CheckItem> listChild(@Param("id") Integer id);

    Integer update(@Param("item") CheckItem checkItem);

    Integer delete(@Param("item") CheckItem checkItem);

    Integer updateState(@Param("item") CheckItem checkItem);

}
