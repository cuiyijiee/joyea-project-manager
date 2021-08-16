package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.ProjectTemplate;
import me.cuiyijie.joyea.domain.Sheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTemplateDao {

    List<ProjectTemplate> findAll(@Param("item") ProjectTemplate projectTemplate);

    Integer insert(@Param("item") ProjectTemplate projectTemplate);

    Integer update(@Param("item") ProjectTemplate projectTemplate);

    Integer delete(@Param("item") ProjectTemplate projectTemplate);

}
