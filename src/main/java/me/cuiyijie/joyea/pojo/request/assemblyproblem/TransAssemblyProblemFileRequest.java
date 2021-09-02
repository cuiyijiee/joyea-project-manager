package me.cuiyijie.joyea.pojo.request.assemblyproblem;

import lombok.Data;

@Data
public class TransAssemblyProblemFileRequest {

    private Integer id;
    private String projectNumber;
    private String fileId;
    private Integer fileType;

}
