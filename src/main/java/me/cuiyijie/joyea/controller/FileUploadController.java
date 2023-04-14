package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.config.Constants;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("file")
@Api(tags = "文件上传模块")
public class FileUploadController {

    @Value("${file.upload.base-path}")
    private String baseUploadPath;

    @PostMapping(value = "/upload")
    public TransBaseResponse upload(@RequestParam("file") MultipartFile multipartFile, @CurrentUser CurrentUserInfo currentUserInfo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        if (multipartFile.isEmpty()) {
            transBaseResponse.setCode(Constants.UPLOAD_FILE_EMPTY_CODE);
            transBaseResponse.setMsg("上传文件为空");
            return transBaseResponse;
        }
        File baseFile = new File(baseUploadPath);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }

        String fileSaveId = UUID.randomUUID().toString();
        String fileSuffix = FileUtil.getFileSuffixWithoutPeriod(multipartFile.getOriginalFilename());

        String serverSavePath = String.format("%s/%s.%s", baseUploadPath, fileSaveId, fileSuffix);
        try {
            multipartFile.transferTo(new File(serverSavePath));
            transBaseResponse.setCode(Constants.SUCCESS_CODE);
            transBaseResponse.setObj(fileSaveId);

        } catch (IOException e) {
            log.error("文件上传出现错误：", e);
        }
        return transBaseResponse;
    }


}
