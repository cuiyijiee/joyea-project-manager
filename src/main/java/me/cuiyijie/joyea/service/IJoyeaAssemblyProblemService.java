package me.cuiyijie.joyea.service;

import com.github.pagehelper.PageInfo;
import me.cuiyijie.joyea.domain.JoyeaAssemblyProblem;
import me.cuiyijie.joyea.domain.JoyeaAssemblyProblemSettings;

public interface IJoyeaAssemblyProblemService {

    PageInfo<JoyeaAssemblyProblem> selectByProjectNumber(String projectNumber, Integer pageSize, Integer pageNum);

    Integer updateProblemSettings(JoyeaAssemblyProblemSettings joyeaAssemblyProblemSettings);

}
