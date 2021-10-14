package me.cuiyijie.joyea.dao.main;

import me.cuiyijie.joyea.model.NextPlusTenantMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NextPlusTenantMemberDao {


    List<NextPlusTenantMember> list(@Param("item") NextPlusTenantMember nextPlusTenantMember);

    void insert(@Param("item") NextPlusTenantMember nextPlusTenantMember);

}
