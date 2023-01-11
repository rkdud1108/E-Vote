package com.evoting.interceptor;

import com.evoting.domain.Member;
import org.hibernate.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqUrl = request.getRequestURL().toString();

        logger.info("[preHandle]");
        HttpSession session = request.getSession();
        Member info = (Member)session.getAttribute("loginUser");

        if(info!=null){
            if(info.getRole().toString().equals("ADMIN")){
                return true;
            }else{
                throw new RuntimeException("ADMIN 계정이 아닙니다.");
            }
        }

        throw new RuntimeException("ADMIN / No access");
    }
}
