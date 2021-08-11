package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.SecurityItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityItemDao {

    Integer insert(@Param("item") SecurityItem securityItem);

    Integer update(@Param("item") SecurityItem securityItem);

    SecurityItem get(@Param("item")SecurityItem securityItem);

    Integer delete(@Param("projectNumber")String projectNumber);

}
