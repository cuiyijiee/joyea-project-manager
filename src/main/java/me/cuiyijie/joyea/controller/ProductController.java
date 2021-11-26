package me.cuiyijie.joyea.controller;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.TransProductRequest;
import me.cuiyijie.joyea.pojo.TransProjectRequest;
import me.cuiyijie.joyea.service.ProductService;
import me.cuiyijie.joyea.util.CheckParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:25
 */
@RestController
@RequestMapping("product")
@Api(tags = "项目产品模块")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "根据projectId查询所有的项目产品", notes = "传入projectId")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProductRequest request) {
        TransBasePageResponse response = new TransBasePageResponse();
        Product selection = new Product();
        BeanUtils.copyProperties(request,selection);
        Page<Product> resultList = productService.list(selection, request.getPageNumber(), request.getPageSize());
        response.setList(resultList.getResult());
        response.setPageNum(resultList.getPageNum());
        response.setPageSize(resultList.getPageSize());
        response.setTotal(resultList.getTotal());
        return response;
    }

    @ApiOperation(value = "新增新的项目产品", notes = "传入projectId，productNumber，productName")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public TransBaseResponse insert(@RequestBody TransProductRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("projectId:项目id（projectId）","productName:产品名称（productName）", "productNumber:产品编号（productNumber）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            response.setCode("0");
            response.setMsg(errorMsg);
            return response;
        }
        Product selection = new Product();
        BeanUtils.copyProperties(request, selection);
        Integer result = productService.insert(selection);
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }
        return response;
    }

    @ApiOperation(value = "删除项目产品", notes = "传入id")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public TransBaseResponse delete(@RequestBody TransProductRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:产品id（id）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            response.setCode("0");
            response.setMsg(errorMsg);
            return response;
        }
        Integer result = productService.delete(request.getId());
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }
        return response;
    }

    @ApiOperation(value = "更新项目产品", notes = "传入id,其余同insert")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public TransBaseResponse update(@RequestBody TransProductRequest request) {
        TransBaseResponse response = new TransBaseResponse();
        List<String> paramsCheck = Lists.newArrayList("id:产品id（id）","productName:产品名称（productName）", "productNumber:产品编号（productNumber）");
        String errorMsg = CheckParamsUtil.checkAll(request, paramsCheck, null, null);
        if (errorMsg != null) {
            logger.error("参数检查错误：" + errorMsg);
            response.setCode("0");
            response.setMsg(errorMsg);
            return response;
        }
        Product selection = new Product();
        BeanUtils.copyProperties(request,selection);
        Integer result = productService.update(selection);
        if (result == 1) {
            response.setCode("0");
        } else {
            response.setCode("-1");
        }
        return response;
    }


}
