package me.cuiyijie.joyea.dao;

import com.github.yulichang.base.MPJBaseMapper;
import me.cuiyijie.joyea.model.CheckItemResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckItemResultDao extends MPJBaseMapper<CheckItemResult> {

    Integer customInsert(@Param("item") CheckItemResult checkItemResult);

}
