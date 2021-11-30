package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.ProjectStageOperation;
import me.cuiyijie.joyea.model.StageProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectStageOperationDao {

    Integer insert(@Param("item") ProjectStageOperation projectStageOperation);

    Integer delete(@Param("item") ProjectStageOperation projectStageOperation);

    Integer insertCheckItemRel(@Param("operationId") Integer operationId, @Param("checkItemId") Integer checkItemId);

    List<ProjectStageOperation> list(@Param("item") StageProduct stageProduct);

    Integer deleteRelByOperationId(@Param("operationRelId") Integer operationRelId);

    List<CheckItem> listCheckItems(@Param("operationRelId") Integer operationRelId);
}
