package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.cuiyijie.joyea.dao.ProcessDao;
import me.cuiyijie.joyea.model.Process;
import me.cuiyijie.joyea.pojo.request.TransProcessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    @Autowired
    private ProcessDao processDao;

    public IPage<Process> select(TransProcessRequest request, Integer pageNum, Integer pageSize) {
        IPage<Process> processPage = new Page<>(pageNum, pageSize);
        return processDao.selectWithPage(processPage, request);
    }

    public String selectCount(TransProcessRequest request) {
        Integer allCount = processDao.selectAllCount(request.getOrderId());
        Integer notStartCount = processDao.selectNotStartCount(request.getOrderId());
        Integer startCount = processDao.selectStartCount(request.getOrderId());
        Integer finishCount = processDao.selectFinishCount(request.getOrderId());

        return String.format("%s_%s_%s_%s", allCount, notStartCount, startCount, finishCount);
    }

}
