package me.cuiyijie.joyea.model.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FileMetadataResponse {

    @JsonProperty("content")
    private List<FileMetadataResponse> content;

    @JsonProperty("path")
    private String path;

    @JsonProperty("is_dir")
    private Boolean isDir;

    @JsonProperty("neid")
    private String neid;

    @JsonProperty("mime_type")
    private String mimeType;
    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("bytes")
    private Long bytes;

    @JsonProperty("desc")
    private String desc;

    private List<FileExtraMetaResponse.ContentDTO.TagsDTO> tags;

    @JsonProperty("download_num")
    private Long downloadNum;

    @JsonProperty("ref_num")
    private Long refNum;

}
