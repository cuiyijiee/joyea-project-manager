package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaPerson;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyeaPersonDao {

    List<JoyeaPerson> list(@Param("joyeaPerson") JoyeaPerson joyeaPerson);

}
