package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.EasCheckItemSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EasCheckItemSettingDao {

    Integer insert(@Param("item") EasCheckItemSetting easCheckItemSetting);

    Integer update(@Param("item") EasCheckItemSetting easCheckItemSetting);

    EasCheckItemSetting select(@Param("operationId") String operationId,@Param("operationNo") String operationNo);

}
