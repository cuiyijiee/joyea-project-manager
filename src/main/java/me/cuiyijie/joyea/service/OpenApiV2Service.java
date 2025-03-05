package me.cuiyijie.joyea.service;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.v2.FileMetadataResponse;
import me.cuiyijie.joyea.model.v2.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiV2Service {

    @Value("${lenovo.api.v2.client.user}")
    private String userName;
    @Value("${lenovo.api.v2.client.pwd}")
    private String userPwd;

    private static final String X_LEVOVO_SESS_ID = "X-LENOVO-SESS-ID";
    private static final String URL_LOGIN = "https://box.lenovo.com/v2/user/login";
    private static final String URL_FILE_METADATA_PATH = "https://box.lenovo.com/v2/metadata/root{path}?path_type={path_type}&limit=10000&X-LENOVO-SESS-ID={X-LENOVO-SESS-ID}";
    private static final String URL_FILE_METADATA_NEID = "https://box.lenovo.com/v2/metadata/root/path?neid={neid}&X-LENOVO-SESS-ID={X-LENOVO-SESS-ID}";

    private final RestTemplate restTemplate;

    private String accessToken;
    private Long expireAt;

    @Synchronized
    public String getAccessToken() {
        if (accessToken == null || System.currentTimeMillis() > expireAt) {
            HttpHeaders headers = new HttpHeaders();
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("user_slug", "email:" + userName);
            map.add("password", userPwd);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, Object>> param = new HttpEntity<>(map, headers);
            ResponseEntity<LoginResponse> response = restTemplate.postForEntity(URL_LOGIN, param, LoginResponse.class);
            LoginResponse tokenResponse = response.getBody();
            accessToken = tokenResponse.getXlenovosessid();
            log.info("刷新到联想网盘v2接口access token：{}", accessToken);
            //expireAt = tokenResponse.getPasswordexpire();
            expireAt = System.currentTimeMillis() + 30 * 60 * 1000;
            return accessToken;
        }
        return accessToken;
    }

    /**
     * 获取文件信息，如果是目录返回子文件夹和文件
     *
     * @param path
     * @return
     */
    public FileMetadataResponse getFileMetadata(String path) {
        ResponseEntity<FileMetadataResponse> response = null;
        try {
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("path", path);
            uriVariables.put("path_type", "ent");
            uriVariables.put(X_LEVOVO_SESS_ID, getAccessToken());
            response = restTemplate.getForEntity(URL_FILE_METADATA_PATH, FileMetadataResponse.class, uriVariables);
        } catch (Exception exception) {
            log.error("请求网盘路径【{}】发生错误：", path, exception);
            return null;
        }
        return response.getBody();
    }

    public FileMetadataResponse getFileInfoByNeid(String neid) {
        ResponseEntity<FileMetadataResponse> response = null;
        try {
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("neid", neid);
            uriVariables.put(X_LEVOVO_SESS_ID, getAccessToken());
            response = restTemplate.getForEntity(URL_FILE_METADATA_NEID, FileMetadataResponse.class, uriVariables);
        } catch (Exception exception) {
            log.error("请求网盘文件ID【{}】发生错误：", neid, exception);
            return null;
        }
        return response.getBody();
    }

}
