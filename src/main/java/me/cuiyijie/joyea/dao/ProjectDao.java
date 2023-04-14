package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.model.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: yjcui3
 * @Date: 2022/9/28 11:28
 */
@Repository
public interface ProjectDao extends BaseMapper<Project> {

    Page<Project> listCollect(IPage<Project> page, @Param("easUserId") String easUserId);

}
