package me.cuiyijie.joyea.pojo.request.projecttemplate;


import lombok.Data;

@Data
public class TransProjectTemplateOperationRequest {

    private Integer templateId;

    private String operationId;
    private String operationNo;

}
