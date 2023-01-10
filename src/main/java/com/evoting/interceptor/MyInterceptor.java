package com.evoting.interceptor;

import com.evoting.domain.Member;
import org.hibernate.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqUrl = request.getRequestURL().toString();

        logger.info("[preHandle]");

        HttpSession session = request.getSession();
        Member info = (Member)session.getAttribute("loginUser");

        if(info==null){
            logger.info(">> interceptor catch!!! userId is null.. ");
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/errors");
            throw new RuntimeException("No access");
        }

        return true;
    }
}
