package me.cuiyijie.joyea.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Edoc2FilePreviewParaResponse {

    @JsonProperty("fileId")
    private Integer fileId;
    @JsonProperty("fileVerId")
    private Integer fileVerId;
    @JsonProperty("convKey")
    private Object convKey;
    @JsonProperty("convType")
    private Object convType;
    @JsonProperty("fileUrl")
    private String fileUrl;
    @JsonProperty("baseUrl")
    private String baseUrl;
    @JsonProperty("controller")
    private String controller;
    @JsonProperty("atFileVerId")
    private Integer atFileVerId;
    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("fileExtName")
    private String fileExtName;
    @JsonProperty("imageSize")
    private Object imageSize;
    @JsonProperty("parentFolderId")
    private Integer parentFolderId;
    @JsonProperty("fileLastVerId")
    private Integer fileLastVerId;
    @JsonProperty("fileLastVerNumStr")
    private Object fileLastVerNumStr;
    @JsonProperty("canPrint")
    private Boolean canPrint;
    @JsonProperty("canPreview")
    private Boolean canPreview;
    @JsonProperty("canEdit")
    private Boolean canEdit;
    @JsonProperty("extData")
    private String extData;
    @JsonProperty("conversionState")
    private Integer conversionState;
    @JsonProperty("fileState")
    private Integer fileState;
    @JsonProperty("token")
    private String token;
    @JsonProperty("code")
    private Object code;
    @JsonProperty("shareCode")
    private Object shareCode;
    @JsonProperty("BrowserPlatform")
    private Integer browserPlatform;
    @JsonProperty("aesfileId")
    private Object aesfileId;
    @JsonProperty("aesfileVerId")
    private String aesfileVerId;
    @JsonProperty("DisablePhoto")
    private Boolean disablePhoto;
}
