package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.config.UserFileType;
import me.cuiyijie.joyea.domain.SysFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemFileDao {

    Integer insert(@Param("checkItemId") String checkItemId, @Param("fileId") String fileId,@Param("fileType") Integer fileType, @Param("checkType")UserFileType checkType);

    List<SysFileUpload> selectByCheckItemId(@Param("checkItemId") String checkItemId, @Param("fileType") Integer fileType, @Param("checkType")UserFileType checkType);

    Integer delete(@Param("id") Integer id);

}
