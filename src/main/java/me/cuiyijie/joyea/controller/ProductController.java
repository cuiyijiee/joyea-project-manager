package me.cuiyijie.joyea.controller;

import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.Project;
import me.cuiyijie.joyea.pojo.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.TransBaseResponse;
import me.cuiyijie.joyea.pojo.TransProductRequest;
import me.cuiyijie.joyea.pojo.TransProjectRequest;
import me.cuiyijie.joyea.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: yjcui3
 * @Date: 2021/10/18 11:25
 */
@Controller
@Api(tags = "项目产品模块")
public class ProductController {

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



}
