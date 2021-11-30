package me.cuiyijie.joyea.dao.main;

import io.swagger.models.auth.In;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.ProjectStage;
import me.cuiyijie.joyea.model.vo.ProductVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yjcui3
 * @Date: 2021/11/30 11:13
 */
@Repository
public interface ProjectStageDao {

    Integer insert(@Param("item") ProjectStage projectStage);

    Integer update(@Param("item") ProjectStage projectStage);

    Integer delete(@Param("stageId") Integer stageId);

    List<ProjectStage> list(@Param("item") ProjectStage projectStage);

    Integer insertStageProduct(@Param("stageId") Integer stageId, @Param("productId") Integer productId);

    Integer deleteStageProductByStageId(@Param("stageId") Integer stageId);

    List<ProductVo> listProductByStageId(@Param("stageId")Integer stageId);
}
