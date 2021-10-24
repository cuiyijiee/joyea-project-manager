package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.Template;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDao {


    List<Template> listAll();

    Integer insert(@Param("item") Template template);

    Integer update(@Param("item") Template template);

    Integer delete(@Param("item") Template template);

}
