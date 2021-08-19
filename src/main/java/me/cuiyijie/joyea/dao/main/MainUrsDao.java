package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.JoyeaUrs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainUrsDao {

    Integer insert(@Param("item") JoyeaUrs joyeaUrs);

    Integer update(@Param("item") JoyeaUrs joyeaUrs);

    Integer delete(String ursId);

    List<JoyeaUrs> selectByProjectNumber(String projectNumber);
}
