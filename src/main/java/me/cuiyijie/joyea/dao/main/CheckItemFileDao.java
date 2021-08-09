package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.domain.SysFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemFileDao {

    Integer insert(@Param("checkItemId") String checkItemId, @Param("fileId") String fileId,@Param("fileType") Integer fileType);

    List<SysFileUpload> selectByCheckItemId(@Param("checkItemId") String checkItemId, @Param("fileType") Integer fileType);

    Integer delete(@Param("id") Integer id);

}
