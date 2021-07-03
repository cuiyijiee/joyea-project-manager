package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.SysFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysFileUploadDao {

    Integer insert(@Param("item") SysFileUpload sysFileUpload);

}
