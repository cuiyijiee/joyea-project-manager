package me.cuiyijie.joyea.dao;

import me.cuiyijie.joyea.model.CheckItemAnswer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: cuiyijie
 * @Date: 2021/12/18 23:11
 */
@Repository
public interface CheckItemAnswerDao {

    Integer insert(@Param("item") CheckItemAnswer checkItemAnswer);

    Integer update(@Param("item") CheckItemAnswer checkItemAnswer);

    CheckItemAnswer selectById(@Param("id") Integer id);

    CheckItemAnswer select(@Param("item") CheckItemAnswer checkItemAnswer);

}
