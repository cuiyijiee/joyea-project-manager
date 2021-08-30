package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.TestSheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestSheetDao {

    Integer insert(@Param("item")TestSheet testSheet);

    List<TestSheet> selectById(Integer id);

}
