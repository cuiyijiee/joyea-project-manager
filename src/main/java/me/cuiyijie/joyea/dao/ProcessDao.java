package me.cuiyijie.joyea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.cuiyijie.joyea.model.Process;
import me.cuiyijie.joyea.pojo.request.TransProcessRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDao extends BaseMapper<Process> {

    IPage<Process> selectWithPage(IPage<Process> page, @Param("item") TransProcessRequest request);

    Integer selectAllCount(@Param("orderId") String orderId);

    Integer selectNotStartCount(@Param("orderId") String orderId);

    Integer selectStartCount(@Param("orderId") String orderId);

    Integer selectFinishCount(@Param("orderId") String orderId);

}
