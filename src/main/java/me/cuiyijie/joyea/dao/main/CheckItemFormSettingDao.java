package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.CheckItemFormSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemFormSettingDao {

    List<CheckItemFormSetting> selectByCheckItemId(@Param("checkItemId") String checkItemId);

}
