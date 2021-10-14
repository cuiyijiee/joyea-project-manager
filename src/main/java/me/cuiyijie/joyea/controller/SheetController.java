//package me.cuiyijie.joyea.controller;
//
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import me.cuiyijie.joyea.domain.Sheet;
//import me.cuiyijie.joyea.pojo.TransBasePageResponse;
//import me.cuiyijie.joyea.pojo.TransBaseResponse;
//import me.cuiyijie.joyea.pojo.request.sheet.TransSheetPageRequest;
//import me.cuiyijie.joyea.pojo.request.sheet.TransSheetRequest;
//import me.cuiyijie.joyea.service.ISheetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("sheet")
//@Api(tags = "表单模板模块")
//public class SheetController {
//
//    @Autowired
//    private ISheetService sheetService;
//
//    @PostMapping(value = "/insert")
//    @ApiOperation(value = "新增表格模板", notes = "传入name")
//    public TransBaseResponse insertSheet(@RequestBody TransSheetRequest request) {
//        TransBaseResponse transBaseResponse = new TransBaseResponse();
//        if (!StringUtils.hasLength(request.getName())) {
//            transBaseResponse.setCode("-1");
//            transBaseResponse.setMsg("参数name为空");
//        } else {
//            Sheet sheet = new Sheet();
//            sheet.setName(request.getName());
//            sheetService.insert(sheet);
//        }
//        return transBaseResponse;
//    }
//
//    @PostMapping(value = "/update")
//    @ApiOperation(value = "更新表格模板", notes = "传入id和name")
//    public TransBaseResponse updateSheet(@RequestBody TransSheetRequest request) {
//        TransBaseResponse transBaseResponse = new TransBaseResponse();
//        if (request.getId() == null || !StringUtils.hasLength(request.getName())) {
//            transBaseResponse.setCode("-1");
//            transBaseResponse.setMsg("参数id或name为空");
//        } else {
//            Sheet sheet = new Sheet();
//            sheet.setId(request.getId());
//            sheet.setName(request.getName());
//            sheetService.update(sheet);
//            transBaseResponse.setCode("0");
//        }
//        return transBaseResponse;
//    }
//
//    @PostMapping(value = "/find")
//    @ApiOperation(value = "查询表格模板", notes = "传入id或name")
//    public TransBasePageResponse findSheet(@RequestBody TransSheetPageRequest request) {
//        TransBasePageResponse transBasePageResponse = new TransBasePageResponse();
//        Sheet sheet = new Sheet();
//        sheet.setId(request.getId());
//        sheet.setName(request.getName());
//        PageInfo<Sheet> result = sheetService.findAll(sheet, request.getPageNum(), request.getPageSize());
//        transBasePageResponse.setPageSize(result.getPageSize());
//        transBasePageResponse.setPageNum(result.getPageNum());
//        transBasePageResponse.setTotal(result.getTotal());
//        transBasePageResponse.setList(result.getList());
//        return transBasePageResponse;
//    }
//
//
//    @PostMapping(value = "/delete")
//    @ApiOperation(value = "删除表格模板", notes = "传入id")
//    public TransBaseResponse deleteSheet(@RequestBody TransSheetRequest request) {
//        TransBaseResponse transBaseResponse = new TransBaseResponse();
//        if (request.getId() == null) {
//            transBaseResponse.setCode("-1");
//            transBaseResponse.setMsg("参数id为空");
//        } else {
//            Sheet sheet = new Sheet();
//            sheet.setId(request.getId());
//            sheetService.delete(sheet);
//            transBaseResponse.setCode("0");
//        }
//        return transBaseResponse;
//    }
//
//}
