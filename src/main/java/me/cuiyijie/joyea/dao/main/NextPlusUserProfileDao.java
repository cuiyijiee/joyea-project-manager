package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.pojo.NextPlusUserProfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NextPlusUserProfileDao {


    Integer insert(@Param("item") NextPlusUserProfile profile);

    Integer update(@Param("item") NextPlusUserProfile profile);

    NextPlusUserProfile selectById(@Param("id") String id);

}
