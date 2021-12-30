package me.cuiyijie.joyea.auth.config;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.auth.CurrentUserInfo;
import me.cuiyijie.joyea.auth.util.JwtUtil;
import me.cuiyijie.joyea.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class JwtAuthenticationInterceptor implements HandlerInterceptor {


    @Value("${server.allow-user}")
    private List<String> allowUsers;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //springboot跨域拦截，放行OPTIONS请求
//        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
//            return true;
//        }
        //获取请求头中的Token
        String token = request.getHeader("X-Token");

        if(token == null) {
            throw new UserException("token verify failed");
        }else if (allowUsers.contains(token)){
            request.setAttribute("currentUserInfo", new CurrentUserInfo(token));
        }else if (!JwtUtil.verifyToken(token)) {
            throw new UserException("token verify failed");
        }else{
            String userId = JwtUtil.parseToken(token);
            request.setAttribute("currentUserInfo", new CurrentUserInfo(userId));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
