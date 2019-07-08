package com.cheer.exam.web.config;

import com.cheer.exam.config.RootConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

// web应用初始化类(Spring容器配置相关类)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override // DispatcherServlet 核心servlet 负责处理所有的http请求然后分发给后面的controller
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //配置字符过滤器 方法二
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

  /*//自定义DispatcherServlet
    //springmvc默认404异常框架不抛出需要设置为true
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
      DispatcherServlet dispatcherServlet = (DispatcherServlet)super.createDispatcherServlet ( servletAppContext );

    }*/

    // 添加servlet初始化参数
    //使404异常不经过tomcat服务器处理，由spring框架处理
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}
