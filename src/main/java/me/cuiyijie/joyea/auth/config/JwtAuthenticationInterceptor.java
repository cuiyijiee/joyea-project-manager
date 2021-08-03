package me.cuiyijie.joyea.auth.config;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.auth.util.JwtUtil;
import me.cuiyijie.joyea.exception.UserException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtAuthenticationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //springboot跨域拦截，放行OPTIONS请求
//        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
//            return true;
//        }
        //获取请求头中的Token
        String token = request.getHeader("X-Token");
        if (token == null || !JwtUtil.verifyToken(token)) {
            throw new UserException("token verify failed");
        }
        String userId = JwtUtil.parseToken(token);
        request.setAttribute("currentUserInfo", new CurrentUserInfo(userId));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}