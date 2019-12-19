package com.dsc.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author DSC
 * @description Web安全配置
 * @date 2019/12/17 17:12
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(new MyIntercepter());
//        //拦截URL
//        addInterceptor.addPathPatterns("/**");
//        //排除URL
//        addInterceptor.excludePathPatterns("/login");
//        addInterceptor.excludePathPatterns("/manager/**");
//        addInterceptor.excludePathPatterns("/test/**");
//        addInterceptor.excludePathPatterns("/templates/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 注意:在生产环境中一般由代理服务器映射相关静态内容
        // log     程序日志目录
        // uploads 上传文件目录

        // 访问 /log/** 的路由映射到 file:log/ 目录下
        registry.addResourceHandler("/log/**").addResourceLocations("file:log/");
        // 访问 /uploads/** 的路由映射到 file:uploads/ 目录下
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
        // 静态文件位置
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
}
