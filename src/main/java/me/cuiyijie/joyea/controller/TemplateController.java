package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.model.vo.TemplateVo;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransOperationRequest;
import me.cuiyijie.joyea.service.CheckItemService;
import me.cuiyijie.joyea.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("template")
@Api(tags = "模板模块")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CheckItemService checkItemService;


    @ApiOperation(value = "获取全部根节点", notes = "获取全部根节点")
    @RequestMapping(value = "listRoot", method = RequestMethod.POST)
    public TransBaseResponse listAll() {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            Template template = new Template();
            template.setIsRoot(true);
            transBaseResponse.setList(templateService.listAll(template));
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("获取全部根节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "获取全部的工序", notes = "获取全部的工序")
    @RequestMapping(value = "listAllOperation", method = RequestMethod.POST)
    public TransBaseResponse listAllOperation(@RequestBody TransOperationRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {

            if(request.getPageNum() != null && request.getPageNum() != -1) {
                List<TemplateVo> operations = templateService.listAllOperation();
            }
            //transBaseResponse.setList(templateService.listAllOperation());
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("获取全部根节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "新增节点", notes = "新增节点")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody TemplateVo templateVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            templateService.insert(templateVo);
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("新增节点出现错误：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "给工序增加文件夹关联关系", notes = "给工序增加文件夹关联关系")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "当前工序ID", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "pid", value = "父级文件夹ID", required = true, dataType = "Integer"),
//    })
    @RequestMapping(value = "addOperationRel", method = RequestMethod.POST)
    public TransBaseResponse addOperationRel(@RequestBody TemplateVo templateVo) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            if (templateVo.getPid() == null) {
                throw new RuntimeException("pid不能为空");
            }
            if (templateVo.getId() != null) {
                templateService.addTemplateRel(templateVo.getPid(), templateVo.getId());
            }
            if (templateVo.getIds() != null && templateVo.getIds().size() > 0) {
                for (int index = 0; index < templateVo.getIds().size(); index++) {
                    int id = templateVo.getIds().get(index);
                    templateService.addTemplateRel(templateVo.getPid(), id);
                }
            }
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("新增节点出现错误：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }


    @ApiOperation(value = "更新节点", notes = "更新节点")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody Template template) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setCode(templateService.update(template) == 1 ? "0" : "-1");
        return transBaseResponse;
    }

    @ApiOperation(value = "删除节点", notes = "删除节点")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody Template template) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            //判断要删除的节点下有没有子节点，如果有子节点不允许删除
            Integer childCount = templateService.selectChildCount(template.getId());
            if (childCount > 0) {
                log.error("删除节点，存在子节点无法删除");
                transBaseResponse.setCode("-1");
                transBaseResponse.setMsg("该节点存在子节点，无法删除！");
                return transBaseResponse;
            }
            templateService.deleteRelByCid(template);
            transBaseResponse.setCode(templateService.delete(template) == 1 ? "0" : "-1");
        } catch (Exception exception) {
            log.error("删除节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

}
