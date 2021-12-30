package me.cuiyijie.joyea.dao;

import me.cuiyijie.joyea.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao {

    Integer insert(@Param("item") Project project);

    List<Project> list(@Param("item") Project project);

    Integer update(@Param("item") Project project);

}
