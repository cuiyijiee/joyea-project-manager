package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaQualityCheckType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyeaQualityCheckTypeDao {

    List<JoyeaQualityCheckType> select();

}
