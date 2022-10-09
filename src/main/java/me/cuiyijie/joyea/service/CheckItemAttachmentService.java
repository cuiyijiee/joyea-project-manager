package me.cuiyijie.joyea.service;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author cyj976655@gmail.com
 * @date 2022/10/9 21:00
 */
@Slf4j
@Service
public class CheckItemAttachmentService {

    @Value("${eas.login.url}")
    private String loginUrl = "http://47.99.45.18:7998/ormrpc/services/EASLogin";
    @Value("${eas.attachment.url}")
    private String getAttachmentUrl = "http://47.99.45.18:7998/ormrpc/services/WSAttachmentFacade";

    private long lastLoginTimestamp = 0L;

    public String getPreviewFilePath() {


        return "";
    }

    public void login() {
        String soap = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:log=\"http://login.webservice.bos.kingdee.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <log:login soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <userName xsi:type=\"xsd:string\">user</userName>\n" +
                "         <password xsi:type=\"xsd:string\">joyea@789</password>\n" +
                "         <slnName xsi:type=\"xsd:string\">eas</slnName>\n" +
                "         <dcName xsi:type=\"xsd:string\">jy20220128</dcName>\n" +
                "         <language xsi:type=\"xsd:string\">l1</language>\n" +
                "         <dbType xsi:type=\"xsd:int\">1</dbType>\n" +
                "      </log:login>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("SOAPAction", "");
        headers.add("Content-Type", "text/xml;charset=UTF-8");
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<String>(soap, headers);
        ResponseEntity<String> response = template.postForEntity(loginUrl, entity, String.class);
        if (response.getStatusCode() == org.springframework.http.HttpStatus.OK) {
            System.out.println("restTemplate返回soap：" + response);
        } else {
            System.out.println("restTemplate返回状态码：" + response.getStatusCode());
        }
    }

    public void getAttachment(String attachmentId) {
        long startTimestamp = System.currentTimeMillis();

        if (lastLoginTimestamp == 0L || System.currentTimeMillis() - lastLoginTimestamp > 30 * 60 * 1000) {
            login();
            lastLoginTimestamp = System.currentTimeMillis();
        }

        log.info("start time: " + startTimestamp);
        try {
            String soap = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.app.common.custom.eas.kingdee.com\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <web:getAttachmentFromFTP soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                    "         <attachmentId xsi:type=\"xsd:string\">" + attachmentId + "</attachmentId>\n" +
                    "      </web:getAttachmentFromFTP>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("SOAPAction", "");
            headers.add("Content-Type", "text/xml;charset=UTF-8");
            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(soap, headers);
            ResponseEntity<String> response = template.postForEntity(getAttachmentUrl, entity, String.class);

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_8))));
            Node node = document.getRootElement().selectSingleNode("//soapenv:Envelope/soapenv:Body");
            if (node != null) {
                String fileBase64 = ((Element) node).elements().get(0).selectSingleNode("getAttachmentFromFTPReturn").getText();
                String originFileName = "/Users/cuiyijie/Desktop/十工位更改前后，及未来图纸对比说明.xlsx";
                base64ToFile(fileBase64, originFileName);
            }
        } catch (Exception exception) {
            log.error("fetch attachment exist error", exception);
        } finally {
            long endTimestamp = System.currentTimeMillis();
            log.info("end time: " + endTimestamp);
            log.info("total time: " + (endTimestamp - startTimestamp) / 1000 + "s");
        }
    }

    public static void base64ToFile(String base64, String fullFilePath) {
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            File file = new File(fullFilePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            byte[] bytes = Base64.getDecoder().decode(base64);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            log.error("transform file error: ", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("close buffer output stream error: ", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("close file output stream error: ", e);
                }
            }
        }
    }

}
