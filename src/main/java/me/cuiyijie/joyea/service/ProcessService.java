package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.dao.ProcessDao;
import me.cuiyijie.joyea.model.Process;
import me.cuiyijie.joyea.pojo.request.TransProcessRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessService extends ServiceImpl<ProcessDao, Process> {

    private final SearchHistoryService searchHistoryService;

    public IPage<Process> select(String easUserId,TransProcessRequest request, Integer pageNum, Integer pageSize) {
        IPage<Process> processPage = new Page<>(pageNum, pageSize);
        if(StringUtils.hasLength(request.getProcessName())) {
            searchHistoryService.addSearchHistory(easUserId,"CACHE_PROCESS_SEARCH_HISTORY",request.getProcessName());
        }
        return baseMapper.selectWithPage(processPage, request);
    }

    public String selectCount(TransProcessRequest request) {
        Integer allCount = baseMapper.selectAllCount(request.getOrderId());
        Integer notStartCount = baseMapper.selectNotStartCount(request.getOrderId());
        Integer startCount = baseMapper.selectStartCount(request.getOrderId());
        Integer finishCount = baseMapper.selectFinishCount(request.getOrderId());

        return String.format("%s_%s_%s_%s", allCount, notStartCount, startCount, finishCount);
    }

}
