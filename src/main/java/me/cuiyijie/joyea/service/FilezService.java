package me.cuiyijie.joyea.service;

import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.exception.SysRuntimeException;
import me.cuiyijie.joyea.pojo.FilezUploadFileRegionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/15 13:01
 */
@Service
@RequiredArgsConstructor
public class FilezService {

    @Value("${filez.access_token.url}")
    private String filezTokenUrl;

    private final RestTemplate restTemplate;

    private String fileUploadDir = "/Eas点检记录附件";

    private final static String UPLOAD_FILE_REGION_URL = "https://api.zbox.filez.com/v3/api/file/fileops/auth";

    private String getAccessToken() {
        Map<String,String> tokenResp = restTemplate.getForObject(filezTokenUrl, HashMap.class);
        String code = tokenResp.get("code");
        if("0".equals(code)) {
            return tokenResp.get("obj");
        }else{
            throw new SysRuntimeException("获取filez accessToken失败！");
        }
    }

    public FilezUploadFileRegionResp getUploadFileRegion(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("bearer %s", getAccessToken()));
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("path", fileUploadDir);
        map.add("path_type", "ent");
        map.add("nsid", "745477");
        map.add("file_name", fileName);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> param = new HttpEntity<>(map, headers);
        ResponseEntity<FilezUploadFileRegionResp> response = restTemplate.postForEntity(UPLOAD_FILE_REGION_URL, param, FilezUploadFileRegionResp.class);
        return response.getBody();
    }
}
