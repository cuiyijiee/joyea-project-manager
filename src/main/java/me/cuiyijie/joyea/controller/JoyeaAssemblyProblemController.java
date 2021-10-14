//package me.cuiyijie.joyea.controller;
//
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import me.cuiyijie.joyea.config.UserFileType;
//import me.cuiyijie.joyea.domain.JoyeaAssemblyProblem;
//import me.cuiyijie.joyea.domain.SysFileUpload;
//import me.cuiyijie.joyea.pojo.TransBasePageResponse;
//import me.cuiyijie.joyea.pojo.TransBaseResponse;
//import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemFileRequest;
//import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemPageRequest;
//import me.cuiyijie.joyea.pojo.request.assemblyproblem.TransAssemblyProblemSettingsRequest;
//import me.cuiyijie.joyea.service.ICheckItemFileService;
//import me.cuiyijie.joyea.service.IJoyeaAssemblyProblemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("assemblyProblem")
//@Api(tags = "曾经问题模块")
//public class JoyeaAssemblyProblemController {
//
//    @Autowired
//    IJoyeaAssemblyProblemService joyeaAssemblyProblemService;
//
//
//    @Autowired
//    ICheckItemFileService checkItemFileService;
//
//    @PostMapping(value = "/findByProject")
//    @ApiOperation(value = "查找曾经问题", notes = "传入projectNumber")
//    private TransBasePageResponse findByProjectNumber(@RequestBody TransAssemblyProblemPageRequest request) {
//        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
//        PageInfo<JoyeaAssemblyProblem> result =
//                joyeaAssemblyProblemService.selectByProjectNumber(request.getProjectNumber(), request.getPageNum(), request.getPageSize());
//        transBasePageResponse.setList(result.getList());
//        transBasePageResponse.setPageNum(result.getPageNum());
//        transBasePageResponse.setPageSize(result.getPageSize());
//        transBasePageResponse.setTotal(result.getTotal());
//        return transBasePageResponse;
//    }
//
//    @PostMapping(value = "/updateSettings")
//    @ApiOperation(value = "更新曾经问题", notes = "根据problemId更新曾经问题")
//    private TransBaseResponse updateUrsSettings(@RequestBody TransAssemblyProblemSettingsRequest request) {
//        TransBaseResponse response = new TransBaseResponse();
//        joyeaAssemblyProblemService.updateProblemSettings(request);
//        response.setCode("0");
//        return response;
//    }
//
//    @ApiOperation(value = "增加曾经问题附件", notes = "增加曾经问题附件")
//    @RequestMapping(value = "file/add", method = RequestMethod.POST)
//    public TransBaseResponse addFile(@RequestBody TransAssemblyProblemFileRequest request) {
//
//        TransBaseResponse response = new TransBaseResponse();
//
//        Integer result = checkItemFileService.insert(request.getProjectNumber(), request.getFileId(),request.getFileType(), UserFileType.AssemblyProblem);
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
//    @ApiOperation(value = "获取曾经问题所有上传附件", notes = "获取曾经问题所有上传附件")
//    @RequestMapping(value = "file/list", method = RequestMethod.POST)
//    public TransBaseResponse getAllFile(@RequestBody TransAssemblyProblemFileRequest request) {
//        TransBaseResponse response = new TransBaseResponse();
//
//        List<SysFileUpload> result = checkItemFileService.selectByCheckItemId(request.getProjectNumber(), request.getFileType(), UserFileType.AssemblyProblem);
//
//        response.setList(result);
//        response.setCode("0");
//
//        return response;
//    }
//
//    @ApiOperation(value = "删除曾经问题指定附件", notes = "删除曾经问题指定附件")
//    @RequestMapping(value = "file/delete", method = RequestMethod.POST)
//    public TransBaseResponse deleteFile(@RequestBody TransAssemblyProblemFileRequest request) {
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
