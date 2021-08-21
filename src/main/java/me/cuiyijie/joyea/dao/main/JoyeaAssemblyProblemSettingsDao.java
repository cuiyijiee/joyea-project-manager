package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.JoyeaAssemblyProblemSettings;
import me.cuiyijie.joyea.domain.JoyeaUrsSettings;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JoyeaAssemblyProblemSettingsDao {

    JoyeaAssemblyProblemSettings findById(String id);

    Integer insert(@Param("item") JoyeaAssemblyProblemSettings joyeaUrsSettings);

    Integer update(@Param("item")JoyeaAssemblyProblemSettings joyeaUrsSettings);

}
