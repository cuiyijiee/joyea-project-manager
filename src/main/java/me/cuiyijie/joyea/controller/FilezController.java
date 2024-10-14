package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.pojo.FilezUploadFileRegionResp;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.FilezService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/15 13:31
 */
@Slf4j
@RestController
@RequestMapping("filez")
@Api(tags = "联想网盘相关接口调用")
@RequiredArgsConstructor
public class FilezController {

    private final FilezService filezService;

    @ApiOperation(value = "获取上传文件", notes = "获取点检项")
    @RequestMapping(value = "upload/region/{filename}", method = RequestMethod.GET)
    public TransBaseResponse getFileUploadRegion(@PathVariable(name = "filename") String fileName) {
        FilezUploadFileRegionResp filezUploadFileRegionResp = filezService.getUploadFileRegion(fileName);
        return TransBaseResponse.success(filezUploadFileRegionResp);
    }
}
