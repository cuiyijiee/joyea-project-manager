package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.CheckItemFormColumnName;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemFormColumnNameDao {

    List<CheckItemFormColumnName> selectByOperationId(@Param("operationId") String operationId);

}
