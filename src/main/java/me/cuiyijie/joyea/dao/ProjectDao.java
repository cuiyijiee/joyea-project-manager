package me.cuiyijie.joyea.dao;

import me.cuiyijie.joyea.domain.JoyeaProject;
import me.cuiyijie.joyea.pojo.vo.JoyeaProjectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDao {

    List<JoyeaProject> getProjectList();

    List<JoyeaProjectVO> selectProject(Map<String, Object> params);

}
