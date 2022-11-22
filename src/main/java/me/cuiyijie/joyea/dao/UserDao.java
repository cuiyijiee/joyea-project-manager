package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.cuiyijie.joyea.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao  extends BaseMapper<User> {

}
