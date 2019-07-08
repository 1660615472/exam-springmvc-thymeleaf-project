package com.cheer.exam.web.interceptor;

import com.cheer.exam.dao.AdminMapper;
import com.cheer.exam.model.Admin;
import com.cheer.exam.model.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Log4j2
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminMapper adminMapper;
    //需要拦截的资源项
    private static final String[] IGNORE_URL = {"/login","/adminLogin","/studentLogin","questionList"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug ( "拦截器启动了》》》》》》》》》》》" );
       String servletPath = request.getServletPath ();
       for(String s :IGNORE_URL){
            if(servletPath.contains ( s )){
                return true;
            }
       }
        Admin admin =(Admin) request.getSession ().getAttribute ( "admin" );
        Student student =(Student) request.getSession ().getAttribute ( "student" );
        if(admin == null&& student == null){
            return false;
        }
        return  true;

    }

    //目标方法的后置处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       System.out.println("后置执行");

    }

    // 在完成之后执行 这个方法在 DispatcherServlet 完全处理完请 求后被调用，可以在该方法中进行一些资源清理的操作。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("完成后执行1。。。");
    }
}
