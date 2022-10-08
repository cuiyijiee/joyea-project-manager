package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.ProcessDao;
import me.cuiyijie.joyea.model.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProcessService {

    @Autowired
    private ProcessDao processDao;

    public Page<Process> select(Process process, Integer pageNum, Integer pageSize) {
        Page<Process> processPage = new Page<>(pageNum, pageSize);

        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(process.getOrderId())) {
            processQueryWrapper.eq("ORDERID", process.getOrderId());
        }

        if (StringUtils.hasLength(process.getProcessName())) {
            processQueryWrapper.and(queryWrapper -> {
                queryWrapper.like("GXNO",process.getProcessName())
                        .or()
                        .like("GXNAME",process.getProcessName());
            });
        }

        processQueryWrapper.orderByAsc("CFSEQ");

        return processDao.selectPage(processPage, processQueryWrapper);
    }

}
