package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.Process;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransProcessRequest;
import me.cuiyijie.joyea.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("process")
@Api(tags = "工序模块")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProcessRequest request) {
        Process process = new Process();
        process.setOrderId(request.getOrderId());
        Page<Process> projectPage = processService.select(process, request.getPageNum(), request.getPageSize());
        return new TransBasePageResponse(projectPage);
    }
}
