package me.cuiyijie.joyea.service;

import EDoc2.IAppService.IOrgAppService;
import EDoc2.IAppService.Model.EDoc2FolderInfo;
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
import me.cuiyijie.joyea.pojo.Edoc2FileInfoResponse;
import me.cuiyijie.joyea.pojo.Edoc2FilePreviewParaResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class EDoc2Service {

    private final TransferConfig transferConfig;
    private final RestTemplate restTemplate;

    private LocalDate refreshTokenDate = LocalDate.now();
    private String token = null;

    public String getAccessToken() {
        if (refreshTokenDate.isBefore(LocalDate.now()) || !StringUtils.hasText(token)) {
            IOrgAppService orgService = FacadeProxy.newProxyInstance(IOrgAppService.class);
            UserLoginIntegrationByUserLoginNameDto userLoginIntegrationByUserLoginNameDto = new UserLoginIntegrationByUserLoginNameDto();
            userLoginIntegrationByUserLoginNameDto.setIntegrationKey("50b6cd80-eb73-0d5b-7804-a1d6b7bfea5f");
            userLoginIntegrationByUserLoginNameDto.setIPAddress("127.0.0.1");
            userLoginIntegrationByUserLoginNameDto.setLoginName("dep_check");
            ReturnValueResult<String> integrationResponse = orgService.UserLoginIntegrationByUserLoginName(userLoginIntegrationByUserLoginNameDto);
            token = integrationResponse.getData();
            refreshTokenDate = LocalDate.now();
        }
        log.info("get edoc2 token: {}", token);
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

    public Edoc2FilePreviewParaResponse getFilePreviewInfo(String fileId) {
        String getFileInfoUrl = String.format("%sapi/services/File/GetFileInfoById?token=%s&fileId=%s", Constants.EDOC2_HOST, getAccessToken(), fileId);
        ReturnValueResult<Edoc2FileInfoResponse> fileInfoResponseReturnValueResult = restTemplate.exchange(getFileInfoUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ReturnValueResult<Edoc2FileInfoResponse>>() {
                }
        ).getBody();

        String fileGuid = fileInfoResponseReturnValueResult.getData().getFileGuid();
        String previewUrl = String.format("%sPreview/GetPreviewPara?t=17293047287531729304728753&clientkey=2&browsertype=app&t=%s&token=%s&fileId=%s&byid=true&fileVerId=0&clientTypeName=h5&deviceTypeName=iphone&browserPlatform=2", Constants.EDOC2_HOST, System.currentTimeMillis(), getAccessToken(), fileGuid);
        ReturnValueResult<Edoc2FilePreviewParaResponse> previewParaResponseReturnValueResult = restTemplate.exchange(previewUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ReturnValueResult<Edoc2FilePreviewParaResponse>>() {
                }
        ).getBody();
        if (previewParaResponseReturnValueResult.getData() != null) {
            Edoc2FileInfoResponse fileInfoResponse = getFileInfoById(previewParaResponseReturnValueResult.getData().getFileId());
            String previewUrl2 = String.format("http://aidoc.joyea.cn:8086/ecm#/preview?uid=MzE3&fileid=%s&moduleId=myvisit&t=15177&displayControl=32768", fileInfoResponse.getFileGuid());
            previewParaResponseReturnValueResult.getData().setPreviewUrl(previewUrl2);
        }
        return previewParaResponseReturnValueResult.getData();
    }

    public Edoc2FileInfoResponse getFileInfoByPath(String path) {
        String getFileInfoUrl = String.format("%sapi/services/File/GetFileInfoByNamePath?token=%s&fileNamePath=%s", Constants.EDOC2_HOST, getAccessToken(), path);
        log.info("edoc2 getFileInfoUrl: {}", getFileInfoUrl);
        ReturnValueResult<Edoc2FileInfoResponse> fileInfoResponseReturnValueResult = restTemplate.exchange(getFileInfoUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ReturnValueResult<Edoc2FileInfoResponse>>() {
                }
        ).getBody();
        return fileInfoResponseReturnValueResult.getData();
    }

    public Edoc2FileInfoResponse getFileInfoById(Integer fileId) {
        String getFileInfoUrl = String.format("%sapi/services/File/GetFileInfoById?token=%s&fileId=%s", Constants.EDOC2_HOST, getAccessToken(), fileId);
        log.info("edoc2 getFileInfoUrl: {}", getFileInfoUrl);
        ReturnValueResult<Edoc2FileInfoResponse> fileInfoResponseReturnValueResult = restTemplate.exchange(getFileInfoUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ReturnValueResult<Edoc2FileInfoResponse>>() {
                }
        ).getBody();
        return fileInfoResponseReturnValueResult.getData();
    }

}
