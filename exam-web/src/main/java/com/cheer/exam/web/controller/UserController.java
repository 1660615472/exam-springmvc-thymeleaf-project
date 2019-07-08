package com.cheer.exam.web.controller;

import com.cheer.exam.model.Admin;
import com.cheer.exam.model.Question;
import com.cheer.exam.service.AdminService;
import com.cheer.exam.service.QuestionService;
import com.cheer.exam.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
public class UserController {
    @Autowired
    AdminService adminService;

    @Autowired
    private QuestionService questionService;


    @GetMapping("login")
    public String login(){
        return "login";
    }

   @GetMapping("adminLogin")
   public String adminlogin(){
        return "adminLogin";
   }


    @PostMapping("adminLogin")
    public String login(/*@Valid*/ Admin admin, /*BindingResult bindingResult,*/ Model model)throws Exception{
      /*  //后台校验数据
        if(bindingResult.hasErrors ()){
            return "adminLogin";
        }*/
        log.debug ( admin+">>>>>>" );
        Admin admin1 = adminService.getAdmin ( admin.getUsername () );
        String encryptPassword = StringUtils.encrypt ( admin.getPassword () );
        if(admin1 == null){
               throw  new RuntimeException ( "账号或密码错误" );
           }
         if(admin.getUsername ().equals ( admin1.getUsername () )&&admin1.getPassword () .equals ( encryptPassword)){
            //往model域写入信息
             model.addAttribute ( "admin",admin );
             log.debug ( admin );

             return "redirect:/questionList";//登陆成功跳转到当前控制器的questionList方法查找到所有题目信息通过model域传给前端页面
         }else {
             log.debug ( "密码错误" );
             return "redirect:/adminLogin";
         }
    }


    @GetMapping("/questionList")
    public String questionList(Model model){
        //找到所有题目
       List<Question> questionList =this.questionService.getQuestionList ();
       log.debug ( questionList+">>>>>>>>>>>>>>>" );
       model.addAttribute ( "questionList",questionList );
       return "questionList";
    }


}
