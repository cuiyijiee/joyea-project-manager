package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.CheckItemRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemRecordDao {


    Integer insert(@Param("item") CheckItemRecord checkItemRecord);

    List<CheckItemRecord> select(@Param("item") CheckItemRecord checkItemRecord);

}
