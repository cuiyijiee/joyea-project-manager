package me.cuiyijie.joyea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUser;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.model.Product;
import me.cuiyijie.joyea.model.ProductSchedule;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.pojo.request.TransProductRequest;
import me.cuiyijie.joyea.service.ProductScheduleService;
import me.cuiyijie.joyea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cuiyijie
 * @Date: 2021/10/18 11:25
 */
@Slf4j
@RestController
@RequestMapping("product")
@Api(tags = "项目产品模块")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductScheduleService productScheduleService;

    @ApiOperation(value = "根据projectId查询所有的项目产品", notes = "传入projectId")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public TransBaseResponse list(@RequestBody TransProductRequest request,@CurrentUser CurrentUserInfo currentUser) {
        IPage<Product> productPage = productService.list(currentUser.getEasUserId(),request,
                request.getPageNum(), request.getPageSize());
        return new TransBasePageResponse(productPage);
    }

    @RequestMapping(value = "findSchedule", method = RequestMethod.POST)
    public TransBaseResponse findSchedule(@RequestBody TransProductRequest request) {
        ProductSchedule productSchedule = new ProductSchedule();
        productSchedule.setOrderId(request.getOrderId());

        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(productScheduleService.select(productSchedule));
        transBaseResponse.setCode("0");

        return transBaseResponse;
    }

    @RequestMapping(value = "count", method = RequestMethod.POST)
    public TransBaseResponse findCount(@RequestBody TransProductRequest request) {
        ProductSchedule productSchedule = new ProductSchedule();
        productSchedule.setOrderId(request.getOrderId());

        TransBaseResponse transBaseResponse = new TransBaseResponse();
        transBaseResponse.setObj(productService.selectCount(request));
        transBaseResponse.setCode("0");

        return transBaseResponse;
    }
}
