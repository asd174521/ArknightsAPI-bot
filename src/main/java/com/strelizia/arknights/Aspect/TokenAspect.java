package com.strelizia.arknights.Aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strelizia.arknights.dao.LoginMapper;
import com.strelizia.arknights.vo.JsonResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Aspect
@Component
@SuppressWarnings({"unused"})
public class TokenAspect {

    public static final String TOKEN_KEY = "Authorization";

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.strelizia.arknights.annotation.Token)")
    public void annotationPointcut() {

    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String token = request.getHeader(TOKEN_KEY);
        String mes;
        if (null == token){
            mes = "no Authorization, please pass Authorization";
        } else {
            Boolean isTrue = this.checkToken(token);
            if (isTrue) {
                loginMapper.refreshToken(token);
                return joinPoint.proceed();
            } else {
                mes = "token is not alive";
            }
        }
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(JsonResult.failureWithCode("301", mes)));
        return null;
    }

    private Boolean checkToken(String token) {
        int len = loginMapper.getToken(token).size();
        return len > 0;
    }

}