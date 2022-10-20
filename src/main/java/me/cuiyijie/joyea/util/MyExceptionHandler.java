package me.cuiyijie.joyea.util;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.config.Constants;
import me.cuiyijie.joyea.exception.SysRuntimeException;
import me.cuiyijie.joyea.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 全局异常处理，反正异常返回统一格式的map
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)//指定拦截的异常
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (exception instanceof UserException) {
            map.put("code", Constants.USER_AUTHENTICATION_EXPIRED_CODE);
            map.put("msg", "用户信息失效");
        } else if (exception instanceof SysRuntimeException) {
            map.put("code", Constants.UNKNOWN_ERROR_CODE);
            map.put("msg", exception.getMessage());
        } else {
            map.put("code", Constants.UNKNOWN_ERROR_CODE);
            map.put("msg", String.format("发生未知错误：%s", exception.getMessage()));
        }
        log.error("全局捕捉到异常：", exception);
        return map;
    }
}