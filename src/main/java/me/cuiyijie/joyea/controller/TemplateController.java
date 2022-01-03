package me.cuiyijie.joyea.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.enums.TemplateLevelType;
import me.cuiyijie.joyea.model.CheckItem;
import me.cuiyijie.joyea.model.Template;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransOperationRequest;
import me.cuiyijie.joyea.pojo.request.TransTemplateRequest;
import me.cuiyijie.joyea.service.CheckItemAnswerService;
import me.cuiyijie.joyea.service.CheckItemService;
import me.cuiyijie.joyea.service.TemplateService;
import me.cuiyijie.joyea.model.vo.TemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ApiOperation(value = "获取子节点", notes = "获取子节点")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "当前文件夹ID", required = true, dataType = "Integer"),
//    })
    @RequestMapping(value = "listChild", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransTemplateRequest request) {
        TransBasePageResponse transBaseResponse = new TransBasePageResponse();
        try {
            Template template1 = templateService.listById(request.getId());
            if (template1 == null) {
                throw new RuntimeException("该节点不存在！");
            }
            if(request.getPageNum() != null) {
                PageHelper.startPage(request.getPageNum(),request.getPageSize());
                if (template1.getLevelType() == TemplateLevelType.DIR) {
                    List<Template> templates = templateService.listChild(request.getId());
                    transBaseResponse = new TransBasePageResponse(new PageInfo(templates));
                } else {
                    List<CheckItem> checkItems = checkItemService.listChild(request.getId());
                    transBaseResponse = new TransBasePageResponse(new PageInfo(checkItems));
                }
            }else{
                if (template1.getLevelType() == TemplateLevelType.DIR) {
                    List<Template> templates = templateService.listChild(request.getId());
                    transBaseResponse.setList(templates);
                } else {
                    List<CheckItem> checkItems = checkItemService.listChild(request.getId());
                    transBaseResponse.setList(checkItems);
                }
            }
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
                PageHelper.startPage(request.getPageNum(),request.getPageSize());
                Template template = new Template();
                template.setName(request.getOperationName());
                List<TemplateVo> operations = templateService.listAllOperation(template);
                transBaseResponse = new TransBasePageResponse(new PageInfo<TemplateVo>(operations));
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
            templateService.deleteTemplate(template);
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("删除节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

    @ApiOperation(value = "批量删除节点", notes = "批量删除节点")
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    public TransBaseResponse batchDelete(@RequestBody TransTemplateRequest request) {
        TransBaseResponse transBaseResponse = new TransBaseResponse();
        try {
            templateService.batchDeleteTemplate(request.getIds());
            transBaseResponse.setCode("0");
        } catch (Exception exception) {
            log.error("删除节点：", exception);
            transBaseResponse.setCode("-1");
            transBaseResponse.setMsg(exception.toString());
        }
        return transBaseResponse;
    }

}
