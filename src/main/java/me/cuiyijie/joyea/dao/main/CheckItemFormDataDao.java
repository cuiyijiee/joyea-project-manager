package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.CheckItemFormData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemFormDataDao {

    List<CheckItemFormData> selectBy(@Param("item") CheckItemFormData checkItemFormData);

}
