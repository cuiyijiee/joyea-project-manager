package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JoyeaOperationDao {

    List<JoyeaOperation> selectByManufactureOrder(@Param("manufactureId") String manufactureId);

    List<JoyeaOperation> findAll(@Param("item") JoyeaOperation joyeaOperation);

}
