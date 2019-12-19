package com.dsc.springboot.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DSC
 * @description 登录拦截器
 * @date 2019/12/17 17:12
 */
public class MyIntercepter implements HandlerInterceptor {
    /**
     * session key
     */
    public final static String SESSION_KEY = "curManager";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Manager manager = (Manager) request.getSession().getAttribute(SESSION_KEY);
//        if (manager == null) {
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
