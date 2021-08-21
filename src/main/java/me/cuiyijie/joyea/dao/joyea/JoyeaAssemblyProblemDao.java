package me.cuiyijie.joyea.dao.joyea;

import me.cuiyijie.joyea.domain.JoyeaAssemblyProblem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyeaAssemblyProblemDao {

    List<JoyeaAssemblyProblem> selectByProjectNumber(String projectNumber);

}
