package me.cuiyijie.joyea.pojo.request.urs;

import lombok.Data;

@Data
public class TransUrsFileRequest {

    private Integer id;
    private String ursId;
    private String fileId;
    private Integer fileType;

}
