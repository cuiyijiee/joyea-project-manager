package me.cuiyijie.joyea.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Edoc2FileInfoResponse {

    @JsonProperty("FileId")
    private Integer fileId;
    @JsonProperty("FileGuid")
    private String fileGuid;
    @JsonProperty("FileName")
    private String fileName;
    @JsonProperty("FileSize")
    private Integer fileSize;
    @JsonProperty("Code")
    private Object code;
    @JsonProperty("FileModifyTime")
    private String fileModifyTime;
    @JsonProperty("EditorName")
    private String editorName;
    @JsonProperty("FileCreateTime")
    private String fileCreateTime;
    @JsonProperty("CreatorId")
    private Integer creatorId;
    @JsonProperty("CreatorName")
    private String creatorName;
    @JsonProperty("FileCreateOperatorName")
    private String fileCreateOperatorName;
    @JsonProperty("CurrentOperator")
    private Object currentOperator;
    @JsonProperty("CurrentOperatorId")
    private Integer currentOperatorId;
    @JsonProperty("CurrentVersionId")
    private Integer currentVersionId;
    @JsonProperty("LastVersionId")
    private Integer lastVersionId;
    @JsonProperty("FileCurVerNumStr")
    private String fileCurVerNumStr;
    @JsonProperty("FileLastVerNumStr")
    private String fileLastVerNumStr;
    @JsonProperty("FileState")
    private Integer fileState;
    @JsonProperty("FileRemark")
    private String fileRemark;
    @JsonProperty("ParentFolderId")
    private Integer parentFolderId;
    @JsonProperty("FilePath")
    private String filePath;
    @JsonProperty("FileNamePath")
    private String fileNamePath;
    @JsonProperty("IncId")
    private Object incId;
    @JsonProperty("FileArchiveTime")
    private String fileArchiveTime;
    @JsonProperty("Permission")
    private Integer permission;
    @JsonProperty("FileType")
    private Integer fileType;
    @JsonProperty("IsDeleted")
    private Boolean isDeleted;
    @JsonProperty("SecurityLevelId")
    private Integer securityLevelId;
    @JsonProperty("SecLevelName")
    private String secLevelName;
    @JsonProperty("SecLevelDegree")
    private Integer secLevelDegree;
    @JsonProperty("EffectiveTime")
    private String effectiveTime;
    @JsonProperty("ExpirationTime")
    private String expirationTime;
    @JsonProperty("IsFavorite")
    private Boolean isFavorite;
    @JsonProperty("FileCipherText")
    private Boolean fileCipherText;
    @JsonProperty("IsCodeRules")
    private Boolean isCodeRules;
    @JsonProperty("FileExtName")
    private String fileExtName;
    @JsonProperty("RelateMode")
    private Integer relateMode;
    @JsonProperty("CanPreview")
    private Boolean canPreview;
    @JsonProperty("CanDownload")
    private Boolean canDownload;
    @JsonProperty("CanDeleteFile")
    private Boolean canDeleteFile;
    @JsonProperty("AttachType")
    private Integer attachType;
    @JsonProperty("FileLastVerExtName")
    private String fileLastVerExtName;
}
