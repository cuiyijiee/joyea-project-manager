package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import me.cuiyijie.joyea.config.Constants;
import me.cuiyijie.joyea.domain.SysFileUpload;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.service.ISysFileUploadService;
import me.cuiyijie.joyea.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("file")
@Api(tags = "文件上传模块")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${file.upload.base-path}")
    private String baseUploadPath;

    @Autowired
    private ISysFileUploadService iSysFileUploadService;

    @PostMapping(value = "/upload")
    public TransBaseResponse upload(@RequestParam("file") MultipartFile multipartFile) {
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

            SysFileUpload sysFileUpload = new SysFileUpload();
            sysFileUpload.setFileSaveId(fileSaveId);
            sysFileUpload.setFileSuffix(fileSuffix);
            sysFileUpload.setOriginFileName(multipartFile.getOriginalFilename());
            iSysFileUploadService.insert(sysFileUpload);

            transBaseResponse.setCode(Constants.SUCCESS_CODE);
            transBaseResponse.setObj(fileSaveId);

        } catch (IOException e) {
            LOGGER.error("文件上传出现错误：", e);
        }
        return transBaseResponse;
    }


}
