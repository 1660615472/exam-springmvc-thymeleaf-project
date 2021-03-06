package com.cheer.exam.web.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.annotation.Resource;

@Configuration//标识这是配置类
@ComponentScan("com.cheer.exam.web")//配置类需要扫描的包路径
@EnableWebMvc//启用springmvc注解
public class WebConfig implements WebMvcConfigurer {
    @Autowired //注入spring容器
    private ApplicationContext applicationContext;

    //注入登陆检查拦截器
    @Resource(name="loginCheckInterceptor")
    private HandlerInterceptor handlerInterceptor;

    //@Resource(name="webInterceptor2")
    private HandlerInterceptor handlerInterceptor2;

/*
    @Autowired
    private StringToDateConvert stringToDateConvert;*/


    //简化静态资源处理方式，直接通过访问
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    //简化页面快捷转向，这样就可以不用配置 Controller 了
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController ( "/samplePage" ).setViewName ( "samplePage" );
    }

    //注册拦截器(有几个注册几个)
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor ( this.handlerInterceptor ).addPathPatterns ( "/*" );
       /* registry.addInterceptor ( this.handlerInterceptor2 ).addPathPatterns ( "/*" );*/
    }


    // 模板解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding ( "UTF-8" );
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode( TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    // 模板引擎
    @Bean
    public SpringTemplateEngine templateEngine(){
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // 视图解析器
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding ( "UTF-8" );
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

   /* //定义日期转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter ( this.stringToDateConvert );
    }*/
}
