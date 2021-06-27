package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaManufactureTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JoyeaManufactureTaskDao {

    List<JoyeaManufactureTask> selectByManufactureOrder(@Param("manufactureId") String manufactureId);

}
