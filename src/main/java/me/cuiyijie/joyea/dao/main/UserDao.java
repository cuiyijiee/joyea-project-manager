package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> listAll(@Param("item") User user);

    Integer insert(@Param("item") User user);

    Integer update(@Param("item") User user);

    Integer delete(@Param("item") User user);

}
