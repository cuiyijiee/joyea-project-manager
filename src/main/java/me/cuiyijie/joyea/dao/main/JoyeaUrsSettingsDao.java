package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.JoyeaUrsSettings;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JoyeaUrsSettingsDao {

    JoyeaUrsSettings findById(String id);

    Integer insert(@Param("item") JoyeaUrsSettings joyeaUrsSettings);

    Integer update(@Param("item")JoyeaUrsSettings joyeaUrsSettings);

}
