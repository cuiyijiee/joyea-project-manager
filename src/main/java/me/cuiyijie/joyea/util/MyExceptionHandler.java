package me.cuiyijie.joyea.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haozz
 * @date 2018/6/19 17:16
 * @description
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 全局异常处理，反正异常返回统一格式的map
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)//指定拦截的异常
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1001);
        map.put("msg", ex.getMessage());
        return map;
    }
}