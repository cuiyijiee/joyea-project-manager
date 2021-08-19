package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaUrs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyeaUrsDao {


    List<JoyeaUrs> selectByProjectNumber(String projectNumber);

}
