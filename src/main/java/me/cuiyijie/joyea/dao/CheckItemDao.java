package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.vo.CheckItemVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemDao extends BaseMapper<CheckItem> {

}
