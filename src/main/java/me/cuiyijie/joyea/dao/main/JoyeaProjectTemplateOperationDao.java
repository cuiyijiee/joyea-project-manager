package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.JoyeaProjectTemplateOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyeaProjectTemplateOperationDao {


    Integer insert(@Param("item") JoyeaProjectTemplateOperation operation);

    Integer delete(@Param("item") JoyeaProjectTemplateOperation operation);

    List<JoyeaProjectTemplateOperation> findByTemplateId(@Param("item") JoyeaProjectTemplateOperation operation);

}
