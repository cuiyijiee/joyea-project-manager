package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaCheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyeaCheckItemDao {

    List<JoyeaCheckItem> selectByOperationIdAndOperationNo(@Param("operationId") String operationId, @Param("operationNo") String operationNo);
}
