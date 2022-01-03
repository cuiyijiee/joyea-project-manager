package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.TemplateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDao {

    Template listById(@Param("id") Integer id);

    List<Template> listAll(@Param("item") Template template);

    List<Template> listChild(@Param("id") Integer id);

    List<TemplateVo> listAllOperation(@Param("item") Template template);

    Integer insert(@Param("item") Template template);

    Integer update(@Param("item") Template template);

    Integer delete(@Param("item") Template template);

    Integer addTemplateRel(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Integer deleteTemplateRel(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Integer selectChildCount(@Param("templateId") Integer templateId);

    Integer deleteRelByCid(@Param("templateId") Integer templateId);
}
