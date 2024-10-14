package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.model.Process;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransProcessRequest;
import me.cuiyijie.joyea.service.ProcessService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("process")
@Api(tags = "工序模块")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProcessRequest request, @CurrentUser CurrentUserInfo currentUser) {
        IPage<Process> projectPage = processService.select(currentUser.getEasUserId(), request,
                request.getPageNum(), request.getPageSize());
        return new TransBasePageResponse(projectPage);
    }

    @RequestMapping(value = "count", method = RequestMethod.POST)
    public TransBaseResponse findCount(@RequestBody TransProcessRequest request) {

        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(processService.selectCount(request));
        transBaseResponse.setCode("0");

        return transBaseResponse;
    }
}
