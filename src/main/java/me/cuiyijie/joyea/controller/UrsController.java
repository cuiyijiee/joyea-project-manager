//package me.cuiyijie.joyea.controller;
//
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import me.cuiyijie.joyea.config.UserFileType;
//import me.cuiyijie.joyea.domain.JoyeaUrs;
//import me.cuiyijie.joyea.model.SysFileUpload;
//import me.cuiyijie.joyea.pojo.TransBasePageResponse;
//import me.cuiyijie.joyea.pojo.TransBaseResponse;
//import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemFileRequest;
//import me.cuiyijie.joyea.pojo.request.urs.TransUrsFileRequest;
//import me.cuiyijie.joyea.pojo.request.urs.TransUrsPageRequest;
//import me.cuiyijie.joyea.pojo.request.urs.TransUrsSettingsRequest;
//import me.cuiyijie.joyea.service.ICheckItemFileService;
//import me.cuiyijie.joyea.service.IJoyeaUrsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("urs")
//@Api(tags = "非标urs模块")
//public class UrsController {
//
//
//    @Autowired
//    IJoyeaUrsService joyeaUrsService;
//
//    @Autowired
//    ICheckItemFileService checkItemFileService;
//
//    @PostMapping(value = "/findByProject")
//    @ApiOperation(value = "查找项目非标URS", notes = "传入projectNumber")
//    private TransBasePageResponse findByProjectNumber(@RequestBody TransUrsPageRequest request) {
//        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
//        PageInfo<JoyeaUrs> result =
//                joyeaUrsService.selectByProjectNumber(request.getProjectNumber(), request.getPageNum(), request.getPageSize());
//        transBasePageResponse.setList(result.getList());
//        transBasePageResponse.setPageNum(result.getPageNum());
//        transBasePageResponse.setPageSize(result.getPageSize());
//        transBasePageResponse.setTotal(result.getTotal());
//        return transBasePageResponse;
//    }
//
//    @PostMapping(value = "/updateSettings")
//    @ApiOperation(value = "更新urs", notes = "根据ursid更新urs")
//    private TransBaseResponse updateUrsSettings(@RequestBody TransUrsSettingsRequest request) {
//        TransBaseResponse response = new TransBaseResponse();
//        joyeaUrsService.updateUrsSettings(request);
//        response.setCode("0");
//        return response;
//    }
//
//    @ApiOperation(value = "增加安全手册附件", notes = "增加安全手册附件")
//    @RequestMapping(value = "file/add", method = RequestMethod.POST)
//    public TransBaseResponse addFile(@RequestBody TransUrsFileRequest request) {
//
//        TransBaseResponse response = new TransBaseResponse();
//
//        Integer result = checkItemFileService.insert(request.getUrsId(), request.getFileId(),request.getFileType(), UserFileType.Urs);
//
//        if (result == 1) {
//            response.setCode("0");
//        } else {
//            response.setCode("-1");
//            response.setMsg("添加出错");
//        }
//        return response;
//    }
//
//    @ApiOperation(value = "获取安全手册所有上传附件", notes = "获取安全手册所有上传附件")
//    @RequestMapping(value = "file/list", method = RequestMethod.POST)
//    public TransBaseResponse getAllFile(@RequestBody TransUrsFileRequest request) {
//        TransBaseResponse response = new TransBaseResponse();
//
//        List<SysFileUpload> result = checkItemFileService.selectByCheckItemId(request.getUrsId(), request.getFileType(), UserFileType.Urs);
//
//        response.setList(result);
//        response.setCode("0");
//
//        return response;
//    }
//
//    @ApiOperation(value = "删除安全手册指定附件", notes = "删除安全手册指定附件")
//    @RequestMapping(value = "file/delete", method = RequestMethod.POST)
//    public TransBaseResponse deleteFile(@RequestBody TransUrsFileRequest request) {
//        TransBaseResponse response = new TransBaseResponse();
//
//        Integer result = checkItemFileService.delete(request.getId());
//        if (result == 1) {
//            response.setCode("0");
//        } else {
//            response.setCode("-1");
//            response.setMsg("删除出现错误");
//        }
//        return response;
//    }
//
//}
