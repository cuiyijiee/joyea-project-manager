package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.CheckItemFormData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemFormDataDao {

    List<CheckItemFormData> selectAllBy(@Param("item") CheckItemFormData checkItemFormData);

    CheckItemFormData selectBy(@Param("item") CheckItemFormData checkItemFormData);

    Integer insert(@Param("item") CheckItemFormData checkItemFormData);

    Integer update(@Param("item") CheckItemFormData checkItemFormData);

}
