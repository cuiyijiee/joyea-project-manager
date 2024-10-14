package me.cuiyijie.joyea.service;

import EDoc2.IAppService.IOrgAppService;
import EDoc2.IAppService.Model.ReturnValueResult;
import EDoc2.IAppService.Model.UserLoginIntegrationByUserLoginNameDto;
import com.edoc2.proxy.FacadeProxy;
import com.modules.TransferConfig;
import com.modules.UpdateStrategy;
import com.transferClient.UploadFileClient;
import com.transferRequest.CreateFileRequest;
import com.transferResponse.CreateFileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.config.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EDoc2Service {

    private final TransferConfig transferConfig;

    public String getAccessToken() {
        IOrgAppService orgService = FacadeProxy.newProxyInstance(IOrgAppService.class);
        UserLoginIntegrationByUserLoginNameDto userLoginIntegrationByUserLoginNameDto = new UserLoginIntegrationByUserLoginNameDto();
        userLoginIntegrationByUserLoginNameDto.setIntegrationKey("50b6cd80-eb73-0d5b-7804-a1d6b7bfea5f");
        userLoginIntegrationByUserLoginNameDto.setIPAddress("127.0.0.1");
        userLoginIntegrationByUserLoginNameDto.setLoginName("dep_check");
        ReturnValueResult<String> integrationReponse = orgService.UserLoginIntegrationByUserLoginName(userLoginIntegrationByUserLoginNameDto);
        String token = integrationReponse.getData();
        log.info("get edoc2 token: {}", integrationReponse);
        return token;
    }

    public CreateFileResponse uploadFile(MultipartFile toUploadFile) {
        UploadFileClient uploadClient = new UploadFileClient(transferConfig);
        CreateFileRequest request = new CreateFileRequest();
        request.setToken(getAccessToken());
        request.setFileName(toUploadFile.getOriginalFilename());
        request.setFileRemark("");
        request.setFileSize(toUploadFile.getSize());  // 真实的文件大小
        request.setFolderId(Constants.DEFAULT_UPLOAD_FOLDER_ID);
        request.setUpdateStrategy(UpdateStrategy.UpdateVersion);
        request.setChunkSize(1500 * 1024 * 1024);  //单位 byte
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(toUploadFile.getInputStream())) {
            CreateFileResponse resp = uploadClient.createFile(request, bufferedInputStream);
            log.info("上传文件成功: {}", resp);
            return resp;
        } catch (Exception e) {
            log.error("上传文件发生异常:", e);
        }
        return null;
    }

}
